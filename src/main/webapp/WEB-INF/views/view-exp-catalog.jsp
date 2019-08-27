<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>Explicit catalog</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	
		<h1>Here is our catalog</h1>
		<div class = "container">
		<nav class="navbar navbar-right">
			<ul class="nav navbar-nav">
				<li>
				<a href ="/view-exp-categories.pcat">Product Categories</a>
				<!-- <a href="/modify-category.pcat?categoryName=${product.categoryName}&categoryDescription=${product.categoryDescription}">
						Modify Category</a></li>
				<li><a
					href="/modify-product.pcat?productName=${product.productName }&productDescription=${product.productDescription}&productColor=${product.productColor}&categoryName=${product.categoryName}&categoryDescription=${product.categoryDescription}
				">Modify
						Product</a></li> -->
			</ul>
		</nav>
		</div>
		<div class="container">
		<ul class="container">
			<sc:forEach items="${products}" var="product">


				<div class="col-md-3">
					<img src="data:image/jpeg;base64,${product.img}" name="productView"
						height="100px" />
						<hr>Name:&nbsp;<b>${product.productName}&nbsp;&nbsp;</b><br>
						Price:&nbsp;<b>${product.productPrice}&nbsp;&nbsp;</b><br>
					Color:&nbsp;${product.productColor}&nbsp;&nbsp;<br> <small>
						${product.productDescription}</small> Category:&nbsp;
					${product.categoryName} &nbsp;<br> Category Description&nbsp;<small>${product.categoryDescription}
						&nbsp;&nbsp;</small> <a class="btn btn-success"
						href="/modify-product.pcat?productPrice=${product.productPrice}&productName=${product.productName }&productDescription=${product.productDescription}&productColor=${product.productColor}&categoryName=${product.categoryName}&categoryDescription=${product.categoryDescription}
				">Modify
						Product</a>&nbsp;&nbsp;
						<hr><br>
				</div>

			</sc:forEach>
		</ul>
		<!--  <li>${product.productName}&nbsp;&nbsp;${product.productColor}&nbsp;&nbsp;
					${product.productDescription} &nbsp;&nbsp; ${product.categoryName}
					&nbsp;&nbsp; ${product.categoryDescription} &nbsp;&nbsp;
					<img src="data:image/jpeg;base64,${product.img}" height = "15px"/><br> <a
					href="/modify-category.pcat?categoryName=${product.categoryName}&categoryDescription=${product.categoryDescription}">
						Modify Category</a> <a
					href="/modify-product.pcat?productName=${product.productName }&productDescription=${product.productDescription}&productColor=${product.productColor}&categoryName=${product.categoryName}&categoryDescription=${product.categoryDescription}
				">Modify
						Product</a>&nbsp;&nbsp;
				</li>-->

			<small>this is not necessary here though, we rather need to
				logout</small>
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

	<%@ include file="../common-files/comfooter.jspf"%>
</body>
</html>