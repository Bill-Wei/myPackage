package com.restrant.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.restrant.biz.OrderDtsBiz;
import com.restrant.biz.OrdersBiz;
import com.restrant.entity.CartItemBean;
import com.restrant.entity.Orderdts;
import com.restrant.entity.Orders;
import com.restrant.entity.Pager;
import com.restrant.entity.Users;

public class OrdersAction extends ActionSupport implements RequestAware,
		SessionAware {

	private int oid;// 查看超链接传递来的参数oid的值。
	private Pager pager;
	private Orders orders;// 用于封装前端页面订单号或订单状态查询时传递过来的属性值

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	OrderDtsBiz orderDtsBiz;
	OrdersBiz ordersBiz;

	public void setOrdersBiz(OrdersBiz ordersBiz) {
		this.ordersBiz = ordersBiz;
	}

	public void setOrderDtsBiz(OrderDtsBiz orderDtsBiz) {
		this.orderDtsBiz = orderDtsBiz;
	}

	Map<String, Object> request;
	Map<String, Object> session;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request  = request;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	// 处理生成订单请求
	public String addOrders() throws Exception {
		Orders orders = new Orders();
		orders.setOrderState("未处理");
		orders.setOrderTime(new Date());
		// 将订单与用户绑定
		Users user = (Users) session.get("user");
		orders.setUsers(user);
		orders.setOrderPrice((Double) session.get("sumPrice"));
		Map cart = (HashMap) session.get("cart");
		// 迭代cart中的键
		Iterator iter = cart.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			// 迭代cart中的键，并取出相对应的CartItemBean对象值
			CartItemBean cartItem = (CartItemBean) cart.get(key);
			// 新建子订单（即订单明细）对象，并将购物车中的餐品及其信息添加至订单中
			Orderdts orderDts = new Orderdts();
			orderDts.setMeal(cartItem.getMeal());
			orderDts.setMealcount(cartItem.getQuantity());
			orderDts.setMealPrice(cartItem.getMeal().getMealPrice());
			orderDts.setOrders(orders);
			// 调用orderDtsBiz实现添加子订单逻辑并将子订单添加至数据库
			orderDtsBiz.addOrderDts(orderDts);
		}
		// 餐品添加至订单后，移除购物车中的餐品信息
		session.remove("cart");
		return "show";
	}

	// 获取指定用户的订单列表 再转到我的订单页面
	public String toMyOrders() throws Exception {
		Users user = (Users) session.get("user");
		List myOrdersList = ordersBiz.getOrdersByUserId(user.getId());
		System.out.println("userid  "+myOrdersList);
		request.put("myOrdersList", myOrdersList);
		return "myorders";
	}

	// 根据订单主表编号获取订单明细列表，再转到我的订单明细页myordersdetails.jsp
	public String toOrdersDetails() throws Exception {
		List ordersDtsList = orderDtsBiz.getOrderDtsByOid(oid);
		request.put("ordersDtsList", ordersDtsList);
		return "toOrdersDetails";
	}

	// 删除指定编号的订单，再转到“toMyOrders”
	public String deleteOrders() throws Exception {
		// 调用业务方法从数据表中删除订单及明细
		ordersBiz.deleteOrdersByOid(oid);
		return "toMyOrders";
	}

	// 获取所有订单列表，再转到订单处理页manageorders.jsp
	public String toManageOrders() throws Exception {

		// 125行~128行--》初始化Pager，即进去页面时先初始化显示第一页的列表
		int curPage = 1;
		if (pager != null) {
			pager.setCurPage(curPage);
		}

		// 131~140行根据查询条件获取列表
		List ordersList = null;
		if (orders != null) {
			// 指定查询条件，则获取满足条件、指定页面的订单列表
			ordersList = ordersBiz.getOrdersByCondition(orders, curPage);
			// 将查询条件存入request范围，将作为分页超链接中的参数值
			if (orders.getOid() != null) {
				request.put("oid", orders.getOid());
			}
			if ((orders.getOrderState() != null)
					&& (!orders.getOrderState().equals(""))) {
				request.put("orderState", orders.getOrderState());
			}
		} else {// orders为空，获取指定页的订单列表
			ordersList = ordersBiz.getAllOrders(curPage);
			// 获取所有菜品数量，用来初始化分页类Pager对象
			pager = ordersBiz.getPagerOfOrders();
		}

		// 设置Pager对象中待显示页的页码
		pager.setCurPage(curPage);
		request.put("ordersList", ordersList);
		return "manageorders";
	}

	// 处理订单，再转到toManageOrders
	public String handleOrders() throws Exception {
		// 调用业务方法从数据表中删除订单及明细
		Orders orders = ordersBiz.getOrdersById(oid);
		orders.setOrderState("已处理");
		ordersBiz.handleOrders(orders);
		return "toManageOrders";
	}
//	删除订单，再转到toShowMeal
	public String deleteOrdersDetails() throws Exception{
		ordersBiz.deleteOrdersByOid(oid);
		return "toMyOrders";
	}
 
}
