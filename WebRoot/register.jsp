<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="/Restrant/css/style.css" type="text/css">
	<title>用户注册</title>
	<script type="text/javascript">
		function checkRegister(){
			var username = document.getElementById("username").value.length;
			if(username==0) {
				alert("请输入用户登录名");
				return false;
			}
			var userpwd = document.getElementById("userpwd").value;
			if(userpwd.length==0||userpwd==null) {
				alert("请输入登录密码");
				return false;
			}
			var userpwd1 = document.getElementById("userpwd1").value;
			if(userpwd1.length==0||userpwd1==null) {
				alert("请再次输入登录密码");
				return false;
			}
			if(userpwd!=userpwd1){
				alert("两次密码输入不一致！");
				return false;
			}
			var tel = document.getElementById("phone").value;
			if(tel!=null&&(!/^1(3|4|5|7|8)\d{9}$/.test(tel))){
				if(!/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(tel)) {
					alert("请输入正确的电话号码或手机号码");
					return false;
				}
			}
			var email = document.getElementById("email").value;
			if(email!=null&&(!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email))){
				alert("请输入正确的邮箱");
				return false;
			}
		}
	</script>
</head>
<body>
	<table width="90%" height="629" border="0" cellpadding="0"
		cellspacing="0" align="center">
		<tr><td width="200" height="101">
				<img src="images/jb_logo.jpg" width="64" height="32"/>
				<strong><span style="font-size: 20px;">网上订餐系统</span></strong>
			</td>
			<td width="640" style="padding-left: 40px;"></td>
		</tr>
		<tr>
			<td height="41" colspan="2" style="background-image: url(images/001.gif);FONT-FAMILY: 宋体;" align="center">
				|<a href="/Restrant/toShowMeal">网站首页</a>|
				<s:if test="(#session.admin==null)&&(#session.user==null)">
				<a href="login.jsp?role=user">用户登录</a>|
				<a href="register.jsp">用户注册</a>|
				<a href="login.jsp?role=admin">管理员登录</a>
				</s:if>
				<s:if test="#session.user!=null">
				<a href="/Restrant/toModifyUser?users.id=${sessionScope.user.id}">修改个人信息</a>|
				<a href="shopCart.jsp">我的购物车</a>|
				<a href="/Restrant/toMyOrders">我的订单</a>|
				<a href="/Restrant/logOut?type=userlogout">注销</a> &nbsp;&nbsp; &nbsp;&nbsp;
				<font style="color: red">欢迎您：${sessionScope.user.trueName}</font>
				</s:if>
				<s:if test="#session.admin!=null">
				<a href="/Restrant/toAddMeal">添加餐品</a>|
				<a href="/Restrant/toManageMeal">管理餐品</a>|
				<a href="/Restrant/toManageOrders">订单处理</a>|
				<a href="/Restrant/logOut?type=adminlogout">注销</a> &nbsp;&nbsp; &nbsp;&nbsp;
				<font style="color: red">欢迎您：${sessionScope.admin.loginName}</font>
				</s:if>
			</td>
		</tr>
		<tr>
			<td valign="top" align="center">
				<p><img src="images/left_top.jpg" width="215" height="100"/>
					<br>
					<img src="images/003.gif" width="191" height="8"/>
				</p></td>
			<td valign="top" width="80%">
				<img src="images/001.jpg" width="595" height="72">
				<br>
				<div style="background-image: url(images/004.gif)">
						&nbsp;
				</div>
				<form action="register" method="post" name="frm" onsubmit="return checkRegister()">
					<table width="100%" cellspacing="10" cellpadding="3" align="center"
						style="text-align: center;border:1px #cccccc solid;">
						<tr style="background-color: #CCCCFF;">
							<td colspan="2">填写注册信息</td>
						</tr>
						<tr><td align="right" style="width: 320px;">登录名称：</td>
							<td align="left"><input type="text" id="username" name="users.loginName" style="width: 220px"/>
							</td></tr>
						<tr><td align="right" style="width: 320px">登录密码：</td>
							<td align="left"><input type="password" id="userpwd" name="users.loginPwd" style="width: 220px;"/>
							</td></tr>
						<tr><td align="right" style="width: 320px">确认密码：</td>
							<td align="left"><input type="password" id="userpwd1" style="width: 220px;"/>
							</td></tr>
						<tr><td align="right" style="width: 320px;">真实姓名：</td>
							<td align="left"><input type="text" name="users.trueName" style="width: 220px"/>
							</td></tr>
						<tr><td align="right" style="width: 320px;">电话号码：</td>
							<td align="left"><input type="text" id="phone" name="users.phone" style="width: 220px"/>
							</td></tr>
						<tr><td align="right" style="width: 320px;">电子邮件：</td>
							<td align="left"><input type="text" id="email" name="users.email" style="width: 220px"/>
							</td></tr>
						<tr><td align="right" style="width: 320px;">默认地址：</td>
							<td align="left"><input type="text" name="users.address" style="width: 220px"/>
							</td></tr>
						<tr style="background-color: #CCCCFF;">
							<td colspan="2"><input name="rigister"  type="submit" id="register" value="注册"/>
							</td></tr> 
					</table>
				</form>
			</td>
		</tr>
		<tr><td colspan="2" align="center">
			<br>
			<hr width="100%">
			<br>
			<br>
			<br>
			</td>
		</tr>
	</table>
</body>
</html>