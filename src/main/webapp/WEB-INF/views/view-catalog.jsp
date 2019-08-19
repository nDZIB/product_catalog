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
			<li>${product.categoryName} [which]
				${product.categoryDescription}<br>
				<a href = "/modify-product.pcat">Modify Product</a>&nbsp;&nbsp;
				<a href = "/modify-category.pcat">Modify Category</a>
			</li>
		</sc:forEach>
	</ul>
</body>
</html>