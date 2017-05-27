package com.restrant.dao;

import java.util.List;

import com.restrant.entity.Orders;

public interface OrdersDAO {
	// 获取指定用户的订单列表
	public List getOrdersByUserId(int userId);

	// 根据订单编号获取订单对象
	public Orders getOrdersById(int oid);

	// 删除订单对象
	public void deleteOrders(Orders orders);

	// 获取指定页显示的订单列表
	public List getAllOrders(int page);

	// 统计所有的订单总数
	public Integer getCountOfAllOrders();

	// 获取满足条件、指定页显示的订单列表
	public List getOrdersByCondition(Orders condition, int page);

	// 统计满足条件的订单总数
	public Integer getCountOfOrders(Orders condition);

	// 更新订单对象（修改订单状态）
	public void updateOrders(Orders orders);
}
