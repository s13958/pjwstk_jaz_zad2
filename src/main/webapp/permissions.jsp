<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="includes/header.html" %>
	<h1>Premium permissions management:</h1>
	<form action="PermissionsServlet" method="get">
		<label>Username: <input type="text" name="username"/></label>
		<input type="submit" value="Change" />
	</form>
	<%@ include file="includes/footer.html" %>
