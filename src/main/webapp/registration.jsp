<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="includes/header.html" %>
	<h1>Registration</h1>
	<form action="RegistrationServlet" method="get">
		<label>Username: <input type="text" name="username"/></label>
		<label>Password: <input type="password" name="password"/></label>
		<label>Confirm Password: <input type="password" name="confirmPassword"/></label>
		<label>Email: <input type="email" name="email"/></label>
		<input type="submit" value="Send" />
	</form>
	<%@ include file="includes/footer.html" %>
