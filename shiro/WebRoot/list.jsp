<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>List Page</h2>
	<!-- 获取授权的用户 -->
	欢迎：<shiro:principal></shiro:principal></br>
	<!-- 用户拥有权限会显示 -->
	<shiro:hasRole name="user">
		<a href="user.jsp">User Page</a></br>
	</shiro:hasRole>
	
	<shiro:hasRole name="admin">
		<a href="admin.jsp">Admin Page</a></br>
	</shiro:hasRole>
	
	<a href="shiro/logout">注销</a></br>
	
	<a href="test">Time test</a>
	
</body>
</html>