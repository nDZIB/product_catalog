<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Overview of catalog</title>
</head>
<body>
	<h1>Here is our catalog</h1>
	<br>
	<ul>
		<sc:forEach items="${products}" var="product">
			<li>${product.productName}&nbsp;&nbsp;
				${product.productColor}&nbsp;&nbsp;
				${product.productDescription} &nbsp;&nbsp; ${product.categoryName}
				&nbsp;&nbsp; ${product.categoryDescription} &nbsp;&nbsp;<br>
			</li>
		</sc:forEach>
	</ul>
	<br>
	<br>
	<br>
	<div>
		<a href = "/user-signup.pcat">Sign Up for account</a>
		<form action="/login.pcat" method="POST">
			<fieldset>
				<legend>Login</legend>
				<label>User Name:&nbsp;</label><input type="text" name="userName"><br>
				<label>Password:&nbsp;</label><input type="password"
					name="userPassword"><br> <input type="submit"
					value="Login">
			</fieldset>
		</form>
	</div>
</body>
</html>