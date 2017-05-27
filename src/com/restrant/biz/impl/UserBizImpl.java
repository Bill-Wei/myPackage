package com.restrant.biz.impl;

import java.util.List;

import com.restrant.biz.UserBiz;
import com.restrant.dao.UserDAO;
import com.restrant.entity.Admin;
import com.restrant.entity.Users;

public class UserBizImpl implements UserBiz {
	UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	// 用户登录验证
	@Override
	public List login(Users condition) {

		return userDAO.search(condition);
	}

	// 管理员登录验证
	@Override
	public List login(Admin condition) {

		return userDAO.search(condition);
	}

	// 添加用户
	@Override
	public void addUser(Users users) {
		userDAO.addUser(users);
	}
//	修改用户
	@Override
	public void modifyUser(Users users) {
		userDAO.updateUser(users);
	}
//	根据用户id获取用户对象
@Override
public Users getUserById(int userId) {
	return userDAO.getUserById(userId);
}
	

}
