<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Restrant/css/style.css">
<title>登录页面</title>
	<script type="text/javascript">
		function loginCheck(){
			if(document.getElementById("loginName").value.length==0){
				alert("请输入用户名");
				return false;
			}
			if(document.getElementById("loginPwd").value.length==0){
				alert("请输入密码");
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
				<strong><span style="font-size: 20px;">网上订单系统</span></strong>
			</td>
			<td width="640" style="padding-left: 40px;"></td>
		</tr>
		<tr>
			<td height="41" colspan="2" style="background-image: url(images/001.gif);FONT-FAMILY: 宋体;" align="center">
				|<a href="/Restrant/toShowMeal">网站首页</a>|
				<a href="login.jsp?role=user">用户登录</a> |
				<a href="register.jsp">用户注册</a> |
				<a href="login.jsp?role=admin">管理员登录</a> 
			</td>
		</tr>
		<tr>
			<td valign="top">
				<img src="images/left_top.jpg" width="215" height="100"/>
			</td>
			<td valign="top" width="80%">
				<img src="images/001.jpg" width="595" height="72" />
				<br>
				<div style="background-image: url(images/004.gif)">
					&nbsp;
				</div>
				<s:if test="#parameters.role[0]=='user'">
				<div style="background-color: #FFCC99;" align="center">
					网上订餐系统用户登录
				</div>
				<br>
				<br>
					<form action="validateLogin?type=userlogin" method="post" name="ufrm" onsubmit="return loginCheck()">
						<table width="263" border="0" cellspacing="6" cellpadding="4" align="center">
							<tr>
								<td width="74"> 用户名:</td>
								<td width="189"><input type="text" name="loginName" id="loginName" style="width:150;"/>
								</td>
							</tr>
							<tr>
								<td> 密&nbsp;&nbsp;&nbsp;码:</td>
								<td><input type="password" name="loginPwd" id="loginPwd" style="width:150" /></td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									<input type="submit" name="login" value="登录" />
								</td>
							</tr>
						</table>
					</form>
				</s:if>
				<s:if test="#parameters.role[0]=='admin'">
					<div style="background-color: #FFCC99;" align="center">
					网上订餐系统管理员登录
				</div>
				<br>
				<br>
					<form action="validateLogin?type=adminlogin" method="post" name="afrm" onsubmit="return loginCheck()">
						<table width="263" border="0" cellspacing="6" cellpadding="4" align="center">
							<tr>
								<td width="74"> 登录名:</td>
								<td width="189"><input type="text" name="loginName" id="loginName" style="width:150;"/>
								</td>
							</tr>
							<tr>
								<td> 密&nbsp;码:</td>
								<td><input type="password" name="loginPwd" id="loginPwd" style="width:150" /></td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									<input type="submit" name="login" value="登录" />
								</td>
							</tr>
							<tr>
								<td colspan="2"></td>
							</tr>
						</table>
					</form>
				</s:if>  
			</td>
		</tr>
		<tr> 
			<td colspan="2" align="center">
					<br>
					<hr width=100%>
					<br>					
					<br>
					<br>
				</td>
			</tr>                                                                                                                                                                                                                                                                                       
</body>
</html>