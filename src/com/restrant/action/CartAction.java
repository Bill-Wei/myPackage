package com.restrant.action;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.New;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.restrant.biz.MealBiz;
import com.restrant.entity.CartItemBean;
import com.restrant.entity.Meal;

public class CartAction extends ActionSupport implements SessionAware {
	// 封装表单传递来的餐品编号mealId参数值
	private Integer mealId;

	public Integer getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	// 封装表单传递来的餐品数量quantity参数值
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	MealBiz mealBiz;

	// 将餐品添加到购物车
	public void setMealBiz(MealBiz mealBiz) {
		this.mealBiz = mealBiz;
	}

	public String addtoshopcart() throws Exception {
		// 从Session中取出购物车，放入Map对象cart中
		Map cart = (Map) session.get("cart");
		// 获取当前要添加到购物车的菜品
		Meal meal = mealBiz.getMealById(mealId);
		// 如果购物车不存在，则创建购物车（实例化HashMap类），并存入session中
		if (cart == null) {
			cart = new HashMap();
			session.put("cart", cart);
		}
		// 如果存在购物车，则判断餐品是否在购物车中
		CartItemBean cartItem = (CartItemBean) cart.get(meal.getMealId());
		if (cartItem != null) {
			// 如果存在购物车中，则更新其数量
			cartItem.setQuantity(cartItem.getQuantity() + 1);
		} else {
			// 不存在则新建cartItemBean实例存入cart中
			cart.put(meal.getMealId(), new CartItemBean(meal, 1));
		}
		// 页面转到shopCart.jsp 。显示购物车
		return "shopCart";
	}

	// 实现 在餐品文本输入框中输入新的数量，文本失去焦点后直接修改购物车中的餐品数量
	public String updateSelectedQuantity() throws Exception {
		// 从session中取出购物车，放入map对象cart中
		Map cart = (Map) session.get("cart");
		CartItemBean cartItem = (CartItemBean) cart.get(mealId);
		cartItem.setQuantity(quantity);
		return "shopCart";

		// Ajax ????
	}

	// 删除购物车中的商品
	public String deleteSelectedOrders() throws Exception {
		// 从session中取出购物车，放入map对象cart中
		Map cart = (Map) session.get("cart");
		cart.remove(mealId);
		return "shopCart";
	}

	// 清空购物车
	public String clearCart() throws Exception {
		// 从session中取出购物车，放入map对象cart中
		Map cart = (Map) session.get("cart");
		cart.clear();
		return "shopCart";
	}

}
