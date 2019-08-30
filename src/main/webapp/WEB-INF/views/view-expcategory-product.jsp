<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>Explicit catalog</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
		<div class = "container">
		
		<nav class="navbar navbar-right">
			<ul class="nav navbar-nav">
				<li><a href ="/view-exp-categories.pcat">Product Categories</a></li>
				<li><a href ="/modify-product.pcat">Add new Product</a></li>
			</ul>
		</nav>
		</div>
		
		
		<div class="container">
		<h1>${category.categoryName}</h1>
		<h4>${category.categoryDescription}</h4><br><br>
		
		<ul class="container">
			<sc:forEach items="${products}" var="product">


				<div class="col-md-3">
				<form action = "/modify-product.pcat" method = "post">
					<img src="data:image/jpeg;base64,${product.img}" name="productView"/>
						<hr>
						<small>Name:&nbsp;</small><b>${product.productName}</b><br>
				<small> Price:&nbsp; </small> <b>${product.productPrice}&nbsp;<span>FCFA</span></b><br>
					<small>${product.productDescription}</small><br> 
				<!--  <a class="btn btn-success" href="/modify-product.pcat?product=${product}&productPrice=${product.productPrice}&productName=${product.productName }&productDescription=${product.productDescription}&productColor=${product.productColor}&categoryName=${product.categoryName}&categoryDescription=${product.categoryDescription}
				">Modify
						Product</a>&nbsp;&nbsp;<br><br> -->
				<input type = "hidden"  name = "productName" value="${product.productName}">
				<input type = "hidden"  name = "productDescription" value="${product.productDescription}">
				<input type = "hidden"  name = "productColor" value="${product.productColor}">
				<input type = "hidden"  name = "productPrice" value="${product.productPrice}">
				<input type = "hidden"  name = "categoryName" value="${product.categoryName}">
				<input type = "hidden"  name = "categoryDescription" value="${product.categoryDescription}">
				<input class = "btn btn-success" type = "submit"  name = "modifyFromView" value="Modify">
				<input class = "btn btn-danger" type = "submit"  name = "deleteFromView" value="Delete">
				</form>
				<hr><br>
				</div>
			</sc:forEach>
		</ul>

			<small>this is not necessary here though, we rather need to
				logout</small>
			<form action="/login.pcat" method="POST">
				<fieldset>
					<legend>Switch User</legend>
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