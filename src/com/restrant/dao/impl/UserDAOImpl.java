package com.restrant.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.restrant.dao.UserDAO;
import com.restrant.entity.Admin;
import com.restrant.entity.Users;

public class UserDAOImpl implements UserDAO {

	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 用户登录验证
	@Override
	public List search(Users condition) {
		List list = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Users.class);
		// 使用Example工具类创建示例对象(类似于Restrictions)
		Example example = Example.create(condition);
		// 为Criteria对象指定示例对象example作为查询条件
		c.add(example);
		list = c.list(); // 执行查询，获得结果
		return list;
	}

	@Override
	public List search(Admin condition) {
		List list = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Admin.class);
		Example example = Example.create(condition);
		c.add(example);
		list = c.list();
		return list;
	}

	// 添加用户
	@Override
	public void addUser(Users users) {
		Session session = sessionFactory.getCurrentSession();
		session.save(users);

	}
//	修改用户
	@Override
	public void updateUser(Users users) {
		Session session = sessionFactory.getCurrentSession();
		session.update(users);
	}
	//根据用户编号获取用户信息
@Override
public Users getUserById(int userId) {
	Session session = sessionFactory.getCurrentSession();
	return (Users)session.get(Users.class, userId);
}
	
	
}
