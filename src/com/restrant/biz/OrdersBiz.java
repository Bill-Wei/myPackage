package com.restrant.biz;

import java.util.List;

import com.restrant.entity.Orders;
import com.restrant.entity.Pager;

public interface OrdersBiz {
	// 获取指定用户的订单列表
	public List getOrdersByUserId(int userId);

	// 删除指定的编号订单
	public void deleteOrdersByOid(int oid);

	// 获取指定页显示的订单列表
	public List getAllOrders(int page);

	// 统计所有的订单总数,用来初始化分页类Pager对象，并设置其perPageRows和rowCount属性
	public Pager getPagerOfOrders();

	// 获取满足条件、指定页显示的订单列表
	public List getOrdersByCondition(Orders condition, int page);

	// 统计满足条件的订单总数，用来初始化分页类Pager对象，并设置其perPageRows和rowCount属性
	public Pager getPagerOfOrders(Orders condition);

	// 更新订单对象（修改订单状态）
	public void handleOrders(Orders orders);

	// 根据订单编号获取订单对象
	public Orders getOrdersById(int oid);
}
