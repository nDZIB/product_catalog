<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>Overview of catalog</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>Here is our catalog</h1>
		
		<br>	
		<ul>
			<li><a href = "/view-exp-catalog.pcat">Product Catalog</a>
			<li><a href = "/modify-category.pcat">Add new Category</a></li>
		</ul>
		<ul class = "container">
			<sc:forEach items="${category}" var="cat">
			<hr>
				<ul class="nav navbar-nav">
				<li>
					Category Name:${cat.categoryName}<br>
					Category Description: ${cat.categoryDescription}<br>
				</li>
				<li><a
					href="/modify-category.pcat?categoryName=${cat.categoryName}&categoryDescription=${cat.categoryDescription}">
						Modify Category</a></li>
				</ul>
				
			</sc:forEach>
		</ul>
			<a href="/user-signup.pcat">Sign Up for account</a>
			<form action="/login.pcat" method="POST" class ="sign-in-form">
				<fieldset>
					<legend>Login</legend>
					<label>User Name:&nbsp;</label><input type="text" name="userName"><br>
					<label>Password:&nbsp;</label><input type="password"
						name="userPassword"><br> <input type="submit"
						value="Login">
				</fieldset>
			</form>
		</div>
	<%@ include file="../common-files/comfooter.jspf"%>
</body>
</html>