<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../common-files/comheader.jspf"%>
<title>Modify-category</title>
</head>
<body>
	<%@ include file="../common-files/comnavbar.jspf"%>
	<div class="container">
		<h1>Modify category</h1>

		<form action="/modify-category.pcat" method="post">
			<a href="#">add as New</a><br> <input type="text"
				value="${category.categoryName}" name="newCategoryName"><br>
			<input type="text" value="${category.categoryDescription}"
				name="newCategoryDescription"><br> <input type="submit"
				value="Save" name="editCategory">&nbsp; <input type="submit"
				value="Delete" name="deleteCategory"> <input type="submit"
				value="Add as new" name="addNewCategory">
		</form>
	</div>
	<%@ include file="../common-files/comfooter.jspf"%>
</body>
</html>
