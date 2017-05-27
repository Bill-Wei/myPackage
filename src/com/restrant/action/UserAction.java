package com.restrant.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.restrant.biz.UserBiz;
import com.restrant.entity.Admin;
import com.restrant.entity.Meal;
import com.restrant.entity.Users;

public class UserAction extends ActionSupport implements SessionAware,RequestAware{

	private String type;
	private String loginName;
	private String loginPwd;
	// 以上这些用于获取前端发送过来的相关属性 值
	private Users users;// 用来封装前端传送过来的Users对象的属性值

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	UserBiz userBiz;

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public String getType() {
		return type;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public void setType(String type) {
		this.type = type;
	}

	Map<String, Object> request;// 用于保存向前台传输的数据

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	Map<String, Object> session;// 用于保存向前台传输的数据

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	// 登录验证
	public String validateLogin() throws Exception {
		List list;
		if ("userlogin".equals(type)) {// 用户
			Users condition = new Users();
			condition.setLoginName(loginName);
			condition.setLoginPwd(loginPwd);
			list = userBiz.login(condition);
			if (list.size() > 0) {
				session.put("user", list.get(0));// 用session以便于所有页面都能获取
			}
		}
		if ("adminlogin".equals(type)) {
			Admin condition = new Admin();
			condition.setLoginName(loginName);
			condition.setLoginPwd(loginPwd);
			list = userBiz.login(condition);
			if (list.size() > 0) {
				session.put("admin", list.get(0));
			}
		}
		// 转到toShowMeal的action
		return "toShowMeal";
	}
//	用户注销
	public String logOut() throws Exception{
		if ("userlogout".equals(type)) {
			session.remove("user");
		}
		if ("adminlogout".equals(type)) {
			session.remove("admin");
		}
		return "toShowMeal";
	}

	// 用户注册 再转到toShowMeal的action
	public String register() throws Exception {
		
		userBiz.addUser(users);
		return "toShowMeal";
	}
	//加载用户信息，再转到modifyUserInfo.jsp
	Users condition = null;
	public String toModifyUser() throws Exception{
//		根据当前的用户获取用户对象并存到request范围内
		System.out.println(users.getId());
		condition = userBiz.getUserById(users.getId());
		System.out.println(condition.getLoginName());
		request.put("userInfo", condition);
			
		return "modifyUserInfo";
	}
//	执行用户信息修改操作，再转到成功页面
	public String doModifyUser() throws Exception {
		if (users.getLoginPwd()==null||users.getLoginPwd().equals("")) {
			users.setLoginPwd(condition.getLoginPwd());
		}
		userBiz.modifyUser(users);
		return "modifySuccess";
	}

}
