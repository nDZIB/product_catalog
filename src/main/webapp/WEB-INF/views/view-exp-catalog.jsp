<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>Explicit catalog</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>Explicit catalog</h1>
		<br>
		<div class="container">
			<nav class="navbar navbar-right">
				<ul class="nav navbar-nav">
					<li><a href="/view-exp-categories.pcat">Product Categories</a></li>
					<li><a href="/modify-product.pcat">Add new Product</a></li>
				</ul>
			</nav>
		</div>
		<br>
		<ul class="container">
			<sc:forEach items="${products}" var="product">
			<div class="col-md-3">
			
			<!-- This form's elements will be hidden. their only role is to ensure that when modifying 
				a product, the search bar is neat (that is, no data is displayed for the user/everything is at the background)-->
			<form action = "/modify-product.pcat" method = "post">
					<img src="data:image/jpeg;base64,${product.img}" name="productView"/>
					<hr>
					<h5>
						<small>Name:&nbsp;</small><b>${product.productName}</b><br>
					</h5>
					<h5>
						<small>Price:</small>&nbsp;<b>${product.productPrice}&nbsp;<span>FCFA</span></b><br>
					</h5>
					<h5>
						<small>Color:</small>&nbsp;${product.productColor}&nbsp;&nbsp;<br>
					</h5>
					<!--  DON'T WANT TO DISPLAY THE PRODUCT DESCRIPTION
					<h5>${product.productDescription}<br>
					</h5>
					-->
					<h5>
						<small>Category:</small>&nbsp; ${product.categoryName} &nbsp;<br>
					</h5>
					<!--  DON'T WANT TO DISPLAY THE CATEGORY DESCRIPTION
					<h5>
						<small>Category Description:</small>&nbsp;<small>${product.categoryDescription}
							&nbsp;&nbsp;</small>
					</h5>
					-->
					
					<!-- THESE HIDDEN FIELDS CONTAIN THE DATA TO BE SENT BASED ON ANY REQUEST BY USER -->
				<input type = "hidden"  name = "productName" value="${product.productName}">
				<input type = "hidden"  name = "productDescription" value="${product.productDescription}">
				<input type = "hidden"  name = "productColor" value="${product.productColor}">
				<input type = "hidden"  name = "productPrice" value="${product.productPrice}">
				<input type = "hidden"  name = "categoryName" value="${product.categoryName}">
				<input type = "hidden"  name = "categoryDescription" value="${product.categoryDescription}">
				<input class = "btn btn-success" type = "submit"  name = "modifyFromView" value="Modify">
				<input class = "btn btn-danger" type = "submit"  name = "deleteFromView" value="Delete">
				</form>
				<hr>
					<br>
				</div>
			</sc:forEach>
		</ul>
		<h1>Switch User</h1>
		<%@ include file = "../common-files/signupform.jspf" %>
	</div>

	<%@ include file="../common-files/comfooter.jspf"%>
</body>
</html>