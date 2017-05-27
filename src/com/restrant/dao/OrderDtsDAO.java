package com.restrant.dao;

import java.util.List;

import javax.persistence.criteria.Order;

import com.restrant.entity.Orderdts;

public interface OrderDtsDAO {
	// 生成子订单表（订单明细）
	public void addOrderDts(Orderdts dts);

	// 根据订单主表编号获取订单明细列表
	public List getOrderDtsByOid(int oid);
}
