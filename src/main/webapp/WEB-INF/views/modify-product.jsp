<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify-prod</title>
</head>
<body>
<h1>Modify products</h1>

<form action = "/modify-product.pcat" method = "post">
<a href="#">add as New</a><br>
<label>Product Name: &nbsp;</label><input type="text" value= "${product.productName}" name = "newProductName"><br>
<label>Product Color: &nbsp;</label><input type= "text" value = "${product.productColor}" name = "newProductColor"><br>
<label>Product Description: &nbsp;</label><input type ="textarea" value ="${product.productDescription}" name = "newProductDescription"><br>
<label>Product category: &nbsp;</label><input type="text" value="${product.categoryName}" name = "newCategoryName"><br>
<label>Category Description: &nbsp;</label><input type ="textarea" value ="${product.categoryDescription}" name = "newCategoryDescription"><br>

<input type ="submit" value = "Save" name ="editProduct">&nbsp;
<input type = "submit" value = "Delete" name = "deleteProduct">
<input type = "submit" value = "Add as new" name = "addNewProduct">
</form>
</html>