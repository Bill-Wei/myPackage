<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加餐品</title>
	<link rel="stylesheet" href="/Restrant/css/style.css" type="text/css">
</head>
<body>
	<table width="90%" height="170" border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td width="200" height="101">
				<img src="images/jb_logo.jpg" width="64" height="32" />
				<strong><span style="font-size:20px;">网上订餐系统</span></strong>
			</td>
			<td width="640" style="padding-left: 40px;">
			</td>
		</tr>
		<tr>
			<td height="41" colspan="2" style="background-image: url(images/001.gif);FONT-FAMILY: 宋体;" align="center">
			|
			<a href="/Restrant/toShowMeal">网站首页</a>|
			<s:if test="(#session.admin==null)&&(#session.user==null)">
			<a href="register.jsp">用户注册</a> |
			<a href="login.jsp?role=user">用户登录</a> |
			<a href="login.jsp?role=admin">管理员登录</a> |
			</s:if>
			<s:if test="#session.user!=null">
			<a href="modifyMyInfo.jsp">修改个人信息</a> |
			<a href="shopCart.jsp">我的购物车</a> |
			<a href="/Restrant/toMyOrders">我的订单</a> |
			<a href="/Restrant/logOut?type=userlogout">注销</a> &nbsp;&nbsp; &nbsp;&nbsp;
			<font style="color: red">欢迎您：${sessionScope.user.trueName }</font>					
			</s:if>
			<s:if test="#session.admin!=null">
			<a href="/Restrant/toAddMeal">添加餐品</a> |
			<a href="/Restrant/toManageMeal">管理餐品</a> |
			<a href="/Restrant/toManageOrders">订单处理</a> |
			<a href="/Restrant/logOut?type=adminlogout">注销</a> &nbsp;&nbsp; &nbsp;&nbsp;
			<font style="color: red">欢迎您：${sessionScope.admin.loginName }</font>
			</s:if>
			</td>
		</tr>
		<tr>
			<td align="center" valign="top">
				<p>
					<img src="images/left_top.jpg" width="215" height="100" />
					<br>
					<img src="images/003.gif" width="191" height="8">
				</p>
			</td>
			<td valign="top" align="center">
				<p>
					<img src="images/001.jpg" width="595" height="72" />
					<br>
					<div style="background-image: url(images/004.gif)">
						&nbsp;
					</div>
					<div style="background-color: #ffcc99;" align="center">
						添加餐品
					</div>
					<br>
					<s:form action="doAddMeal" method="post" enctype="multipart/form-data">
						<table align="center">
							<s:textfield name="meal.mealName" label="菜名"></s:textfield>
							<s:select name="meal.mealseries.seriesId" label="菜系"
								list="#request.mealSeriesList" listKey="seriesId"
								listValue="seriesName"></s:select>
							<s:textfield name="meal.mealSummarize" label="摘要"></s:textfield>
							<s:textfield name="meal.mealDescription" label="介绍"></s:textfield>
							<s:textfield name="meal.mealPrice" label="价格"></s:textfield>
							<s:file name="doc" label="图片"></s:file>
							<s:submit value="确定" align="center"></s:submit>
						</table>
					</s:form>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<br>
					<hr width="100%">
					<br>
				</td>
			</tr>
	</table>
	
	
</body>
</html>