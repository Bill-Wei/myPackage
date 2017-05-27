package com.restrant.biz;

import java.util.List;

import com.restrant.entity.Admin;
import com.restrant.entity.Users;

public interface UserBiz {
	// 用户登录验证
	public List login(Users condition);

	// 管理员登录验证
	public List login(Admin condition);

	// 添加用户（用户注册）
	public void addUser(Users users);
	
//	修改用户（用户信息修改）
	public void modifyUser(Users users);
	
//	根据用户id获取用户对象
	public Users getUserById(int userId);

}
