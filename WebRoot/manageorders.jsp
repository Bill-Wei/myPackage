<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 根据订单编号和订单状态查询form -->
	<s:form theme="simple" action="toManageOrders" method="post">
		<s:label value="订单号"></s:label>
		<s:textfield name="orders.oid"></s:textfield>
		<s:label value="订单状态"></s:label>
		<s:select list="#{'全部':'全部','未处理':'未处理','已处理':'已处理'}"
				name="orders.orderState" listKey="key" listValue="value"></s:select>
		<s:submit value="查询"></s:submit>
	</s:form>

	<!-- 循环显示订单列表 -->
	<s:set var="tatal" value="0"></s:set>
	<s:iterator id="orders" value="#request.ordersList">
		<tr style="background-color: #FFFFFF;">
			<td><s:property value="oid"/></td>
			<td><s:date name="orderTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td><s:property value="orderState"/></td>
			<td><s:property value="orderPrice"/></td>
			<td>
				<s:if test="#orders.orderState='未处理'">
					<a href="/Restrant/handleOrders?oid=${oid}">
						<img src="images/handle.gif" width="12" height="12">
					</a>
				</s:if>
			</td>
		</tr>
	</s:iterator>
	
	<!-- 分页超链接开始 -->
	<table align="right">
		<tr>
			<td width="130"></td>
			<td width="80">
				<s:if test="pager.curPage>1">
					<a href="/Restrant/toShowMeal?pager.curPage=1&
					meal.mealseries.seriesId=${requestScope.seriesId }&
					meal.mealName=${requestScope.mealName}">首页</a>&nbsp;&nbsp;
					<a href="/Restrant/toShowMeal?pager.curPage=${pager.curPage-1}&
					meal.mealseries.seriesId=${requestScope.seriesId }&
					meal.mealName=${requestScope.mealName}">上一页</a>
				</s:if>
			</td>
			<td width="80">
				<s:if test="pager.curPage<pager.pageCount">
					<a href="/Restrant/toShowMeal?pager.curPage=${pager.curPage+1}&
					meal.mealseries.seriesId=${requestScope.seriesId }&
					meal.mealName=${requestScope.mealName}">下一页</a>&nbsp;&nbsp;
					<a href="/Restrant/toShowMeal?pager.curPage=${pager.pageCount}&
					meal.mealseries.seriesId=${requestScope.seriesId }&
					meal.mealName=${requestScope.mealName}">尾页</a>
				</s:if>
			</td>
			<td>共${pager.rowCount}条记录，共${pager.curPage}/${pager.pageCount}页 &nbsp;&nbsp;
			</td>
		</tr>
	</table>
	<!-- 分页超链接部分结束 -->
</body>
</html>