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
			<input type="text" value="${category.categoryName}"
				name="newCategoryName"><br> <input type="text"
				value="${category.categoryDescription}"
				name="newCategoryDescription"><br><br><input type="submit"
				value="Add" name="addNewCategory">
		</form>
	</div>
	<%@ include file="../common-files/comfooter.jspf"%>
</body>
</html>
