package com.restrant.dao;

import java.util.List;

import com.restrant.entity.Admin;
import com.restrant.entity.Users;

public interface UserDAO {

	// 用户登录验证
	public List search(Users condition);

	// 管理员登录验证
	public List search(Admin condition);

	// 添加用户
	public void addUser(Users users);
	
//	修改用户
	public void updateUser(Users users);
//	根据用户编号获取用户信息
	public Users getUserById(int userId);

}
