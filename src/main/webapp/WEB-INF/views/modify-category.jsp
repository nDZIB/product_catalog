<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify-category</title>
</head>
<body>
<h1>Modify category</h1>

<form action = "/modify-category.pcat" method = "post">
<a href="/add-new-category">Add new Category</a><br>
<input type="text" value="${category.categoryName}" name = "newCategoryName"><br>
<input type ="text" value ="${category.categoryDescription}" name = "newCategoryDescription"><br>
<input type ="submit" value = "Edit" name ="editCategory">&nbsp;
<input type = "submit" value = "Delete" name = "deleteCategory">
</form>
</html>