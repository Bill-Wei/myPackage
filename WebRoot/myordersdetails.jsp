<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:set var="count" value="0"></s:set>
	<s:iterator id="ordersDtsItem" value="#request.ordersDtsList">
		<tr style="background-color: #FFFFFF;">
			<td><s:property value="odid"/></td>
			<td><s:property value="meal.mealName"/></td>
			<td><s:property value="mealPrice"/></td>
			<td><s:property value="mealCount"/></td>
			<td><s:property value="mealPrice*mealCount"/></td>
		</tr>
		<s:set var="count" value="#count+mealPrice*mealCount"></s:set>
	</s:iterator>
</body>
</html>