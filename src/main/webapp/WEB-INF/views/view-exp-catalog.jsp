<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Explicit catalog</title>
</head>
<body>
	<h1>Here is our catalog</h1>
	<br>
	<ul>
		<sc:forEach items="${products}" var="product">
			<li>${product.productName}&nbsp;&nbsp;${product.productColor}&nbsp;&nbsp;
				${product.productDescription} &nbsp;&nbsp; ${product.categoryName}
				&nbsp;&nbsp; ${product.categoryDescription} &nbsp;&nbsp;<br>
				<a href="/modify-category.pcat?categoryName=${product.categoryName}&categoryDescription=${product.categoryDescription}">
					Modify Category</a>
				<a href="/modify-product.pcat?productName=${product.productName }&productDescription=${product.productDescription}&productColor=${product.productColor}&categoryName=${product.categoryName}&categoryDescription=${product.categoryDescription}
				">Modify Product</a>&nbsp;&nbsp;	
			</li>
		</sc:forEach>
	</ul>
	<br>
	<br>
	<br>
	<div>
	
	<small>this is not necessary here though, we rather need to logout</small>
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