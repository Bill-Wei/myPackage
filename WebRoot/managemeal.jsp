<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Restrant/css/style.css" type="text/css">
<title>餐品后台管理</title>
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
				<div align="left">
					<s:form theme="simple" method="post" action="toManageMeal">
						<s:label value=" 输入菜名"></s:label>
						<s:textfield name="meal.mealName"></s:textfield>
						<s:hidden name="meal.mealseries.seriesId" value="%{#request.seriesId}"></s:hidden>
						<s:submit value="查询"></s:submit>
					</s:form>
				</div>
				<br>
				<div style="background-image: url(images/004.gif)">
					&nbsp;
				</div>
				<br><br>
				<table width="75%" cellspacing="0" cellpadding="4" align="center"
						 style="text-align: center;border: 1px #cccccc solid;">
					<tr style="background-color: #CCCCFF;">
						<td>菜系</td>
						<td>菜名</td>	
						<td>摘要</td>	
						<td>价格</td>	
						<td>修改</td>	
						<td>删除</td>
					</tr>
				<!-- 餐品循环 -->
				<s:iterator id="mealItem" value="#request.mealList" status="st">
					<tr>
						<td><s:property value="mealseries.seriesName"/></td>
						<td><s:property value="mealName"/></td>
						<td><s:property value="mealSummarize"/></td>
						<td><s:property value="mealPrice"/></td>
						<td><a href="/Restrant/toUpdateMeal?meal.mealId=${mealItem.mealId}">
							修改</a></td>
						<td><a href="/Restrant/deleteMeal?meal.mealId=${mealItem.mealId}">
							删除</a></td>
					</tr>
				</s:iterator>
				
				<!-- 分页超链接 -->
				<table align="right">
					<tr>
						<td width="130"></td>
						<td width="80">
							
				</table>		 
				</table>
				
	</table>
	

	

</body>
</html>