<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车页面</title>
<link rel="stylesheet" href="/Restrant/css/style.css" type="text/css">
</head>
<body>
	<table width="90%" height="629" border="0" cellpadding="0"
		cellspacing="0" align="center">
		<tr><td width="200" height="101"><!-- 右上角网页图标及名字部分 -->
				<img src="images/jb_logo.jpg" width="64" height="32"/>
				<strong><span style="font-size: 20px;">网上订餐系统</span></strong>
			</td>
			<td width="640" style="padding-left: 40px;"></td>
		</tr>
		<tr><!-- 包含 网站首页、用户登录等连接的导航条 -->
			<td height="41" colspan="2" style="background-image: url(images/001.gif); FONT-FAMILY: 宋体;" align="center">
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
				<font style="color: red" class="font">欢迎您：${sessionScope.user.trueName}</font>
				</s:if>
				<s:if test="#session.admin!=null">
				<a href="/Restrant/toAddMeal">添加餐品</a>|
				<a href="/Restrant/toManageMeal">管理餐品</a>|
				<a href="/Restrant/toManageOrders">订单处理</a>|
				<a href="/Restrant/logOut?type=adminlogout">注销</a> &nbsp;&nbsp; &nbsp;&nbsp;
				<font style="color: red" class="font">欢迎您：${sessionScope.admin.loginName}</font>
				</s:if>
			</td>
		</tr>
		<tr>
			<td valign="top" align="center"><!-- 页面左边图片 -->
				<p><img src="images/left_top.jpg" width="215" height="100"/>
					<br>
					<img src="images/003.gif" width="191" height="8"/>
				</p></td>
			<td valign="top" width="80%"><!-- 页面右边图片部分 -->
				<img src="images/001.jpg" width="595" height="72">
				<br>
				<div style="background-image: url(images/004.gif)">
						&nbsp;
				</div>
				<img src="images/zdkf.jpg" style="margin-top: 4px;" align="top">
				<font style="font-size: 12px; FONT-FAMILY: 宋体">您的购物车中有以下商品</font>
				<br>
				<hr>
				<table align="center" width="95%" cellspacing="0" cellpadding="3"
					style="text-align: center;border: 1px #cccccc solid; font-size: 12px;FONT-FAMILY: 宋体;">
					<tr style="background-color: #ccccff;">
						<td>编号</td>
						<td>商品名称</td>
						<td>单价</td>
						<td>数量</td>
						<td>金额</td>
						<td>删除</td>
					</tr>
					<s:set var="sumPrice" value="0"></s:set>
					<s:iterator id="cartItem" value="#session.cart">
					<tr style="background-color:#ccccff;">
						<td><s:property value="value.meal.mealId"/></td>
						<td><s:property value="value.meal.mealName"/></td>
						<td>￥<s:property value="value.meal.mealPrice"/></td>
						<td><input type="text" value="${value.quantity}" size="10"
								onchange="window.location='updateSelectedQuantity?mealId=
								${value.meal.mealId}&quantity='+this.value;">
						</td>
						<td>￥<s:property value="value.quantity*value.meal.mealPrice"/></td>
						<td><a href="deleteSelectedOrders?mealId=${value.meal.mealId}">删除</a></td>
					</tr>
					<s:set var="sumPrice" value="#sumPrice+value.quantity*value.meal.mealPrice"></s:set>
					</s:iterator>
					<tr style="background-color:#ccccFF;">
						<td>合计</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>￥<s:property value="#sumPrice"/>
							<s:set var="sumPrice" value="#sumPrice" scope="session"/>
						</td>
						<td>-</td>
					</tr>
				</table>
				<br>
				<table width="300" cellspacing="0" cellpadding="4" align="center"
						style="text-align: center;border: 1px #cccccc solid;">
					<tr style="background-color: #ccccff;">
						<td><a href="/Restrant/clearCart">清空购物车</a></td>	
						<td><a href="/Restrant/toShowMeal">继续购物</a></td>	
						<td><a href="/Restrant/addOrders">生成订单</a></td>	
					</tr>	
				</table>
			</td>
		</tr>
		<tr><td colspan="2" align="center">
			<br>
			<hr width="100%">
			<br>
			</td>
		</tr>
	</table>
	</body>
</html>