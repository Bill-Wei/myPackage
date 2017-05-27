package com.restrant.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.restrant.dao.OrderDtsDAO;
import com.restrant.entity.Orderdts;
import com.restrant.entity.Orders;

public class OrderDtsDAOImpl implements OrderDtsDAO {

	SessionFactory sessionFactory;

	// 生成子订单表（订单明细）
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addOrderDts(Orderdts dts) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(dts);

	}

	// 根据订单主表编号获取订单明细列表
	@Override
	public List getOrderDtsByOid(int oid) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Orderdts.class);
		c.add(Restrictions.eq("orders.id", oid));
		return c.list();
	}

}
