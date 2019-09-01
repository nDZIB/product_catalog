<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="../common-files/comheader.jspf"%>
<title>Modify-prod</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>Add product</h1>


		<form action="/modify-product.pcat" method="post"
			enctype="multipart/form-data">
			<label>Product Name: &nbsp;</label><input class =  "form-control" type="text"
				value="${product.productName}" name="newProductName"><br>
			<label>Product Color: &nbsp;</label><input class =  "form-control" type="text"
				value="${product.productColor}" name="newProductColor"><br>
			<label>Product Description: &nbsp;</label><input class =  "form-control" ype="text"
				value="${product.productDescription}" name="newProductDescription"><br>
			<label>Product category: &nbsp;</label><input class =  "form-control" type="text"
				value="${product.categoryName}" name="newCategoryName"><br>
			<label>Category Description: &nbsp;</label><input class =  "form-control" type="text"
				value="${product.categoryDescription}" name="newCategoryDescription"><br>
			<label>Price:</label><input class =  "form-control" type="text"
				value="${product.productPrice}" name="newProductPrice"><br>

			
			<label>Product Picture</label><input class = "btn btn-default" type="file" name="productView"
				value="5.jpg" /><br> 
			<input class = "btn btn-success" type="submit" value="Add"
				name="addNewProduct">

		</form>
	</div>
	<%@ include file="../common-files/comfooter.jspf"%>
</body>
</html>