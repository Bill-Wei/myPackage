package com.restrant.biz.impl;

import java.util.List;

import com.restrant.biz.OrdersBiz;
import com.restrant.dao.OrdersDAO;
import com.restrant.entity.Orders;
import com.restrant.entity.Pager;

public class OrdersBizImpl implements OrdersBiz {

	OrdersDAO ordersDAO;

	public void setOrdersDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}

	// 获取指定用户的订单列表
	@Override
	public List getOrdersByUserId(int userId) {
		return ordersDAO.getOrdersByUserId(userId);
	}

	// 根据订单编号删除订单对象
	@Override
	public void deleteOrdersByOid(int oid) {
		// 首先 获取订单对象
		Orders orders = ordersDAO.getOrdersById(oid);
		// 再删除获取到的对象
		ordersDAO.deleteOrders(orders);
	}

	// 获取指定页显示的订单列表
	@Override
	public List getAllOrders(int page) {
		return ordersDAO.getAllOrders(page);
	}

	// 统计所有的订单总数,用来-->初始化分页类Pager对象<--，并设置其perPageRows和rowCount属性
	@Override
	public Pager getPagerOfOrders() {
		// 首先获取所有订单数量
		int count = ordersDAO.getCountOfAllOrders();
		Pager pager = new Pager();// 实例化Pager对象，用来将订单进行分页
		// 设置每一页显示的订单数
		pager.setPerPageRows(6);
		// 设置记录总数
		pager.setRowCount(count);
		return pager;
	}

	@Override
	public List getOrdersByCondition(Orders condition, int page) {
		return ordersDAO.getOrdersByCondition(condition, page);
	}

	@Override
	public Pager getPagerOfOrders(Orders condition) {
		int count = ordersDAO.getCountOfOrders(condition);
		Pager pager = new Pager();
		pager.setPerPageRows(6);
		pager.setRowCount(count);
		return pager;
	}

	// 更新订单对象（修改订单状态）
	public void handleOrders(Orders orders) {
		ordersDAO.updateOrders(orders);
	}

	// 根据订单编号获取订单对象
	public Orders getOrdersById(int oid) {
		return ordersDAO.getOrdersById(oid);
	}

}
