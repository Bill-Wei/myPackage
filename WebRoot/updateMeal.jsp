<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Restrant/css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>

		<table width="90%" height="629" border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td width="200" height="101">
				<img src="images/jb_logo.jpg" width="64" height="32">
				<strong><span style="font-size:20px">网上订餐系统</span> </strong>
			</td>
			<td width="640" style="padding-left: 40px;"></td>	
		</tr>
		<tr>
			<td height="41" colspan="2" style="background-image: url(images/001.gif);FONT-FAMILY: 宋体;" align="center">
				| <a href="/Restrant/toShowMeal">网站首页</a> |
					<a href="/Restrant/toAddMeal">添加餐品</a> |
					<a href="/Restrant/toManageMeal">管理餐品</a> |
					<a href="/Restrant/toManageOrders">订单处理</a> |
					<a href="/Restrant/logOut?type=adminlogout">注销</a> &nbsp;&nbsp; &nbsp;&nbsp;
					<font style="color: red">欢迎您：${sessionScope.admin.loginName }</font>
			</td>
		</tr>
		<tr>
			<td valign="top" align="center">
				<img src="images/left_top.jpg" width="215" height="100" />
				<br>
				<img src="images/003.gif" width="191" height="8">
				<!-- 菜系循环开始 -->
				<s:iterator id="mealSeries" value="#request.mealSeriesList"> <br>
					<a href="/Restrant/toShowMeal?meal.mealseries.seriesId=${mealSeries.seriesId}">
						${mealSeries.seriesName}
					</a><br>
				</s:iterator>
				<!-- 菜系循环结束 -->
			</td>
			<td valign="top" width="80%">
				<img src="images/001.jpg" width="595" height="72" />
				<br>
				<%-- <div align="left">
					<s:form theme="simple" method="post" action="toManageMeal">
						<s:label value=" 输入菜名"></s:label>
						<s:textfield name="meal.mealName"></s:textfield>
						<s:hidden name="meal.mealseries.seriesId" value="%{#request.seriesId}"></s:hidden>
						<s:submit value="查询"></s:submit>
					</s:form>
				</div>
				<br> --%>
				<div style="background-image: url(images/004.gif)">
					&nbsp;
				</div>
				<br><br>
				<!-- 绑定表单元素 -->
	<s:form action="doUpdateMeal" method="post" enctype="multipart/form-data">
		<table align="center">
			<s:hidden name="meal.mealId" value="%{#request.updateMeal.mealId}"/>
			<s:textfield name="meal.mealName" label="菜名" value="%{#request.updateMeal.mealName}"/>
			
			<s:select name="meal.mealseries.seriesId" babel="菜系"
						value="%{#request.updateMeal.mealseries.seriesId}"
						list="#request.mealSeriesList" listKey="seriesId"
						listValue="seriesName"/>
			
			<s:textfield name="meal.mealSummarize" label="摘要" 
					value="%{#request.updateMeal.mealSummarize}"></s:textfield>
			<s:textfield name="meal.mealDescription" label="介绍" 
						value="%{request.updateMeal.mealDescription}"/>
			<s:textfield name="meal.mealPrice" label="价格" 
					value="%{#request.updateMeal.mealPrice}"></s:textfield>
			<s:hidden name="meal.mealImage" value="%{#request.updateMeal.mealImage}"></s:hidden>
			<s:file name="doc" label="图片"></s:file>
			<s:submit value="确定" align="center"></s:submit>
		</table>
	</s:form>
				
	</table>
		
		
	
</body>
</html>