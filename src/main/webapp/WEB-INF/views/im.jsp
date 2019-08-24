<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sc"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Some stupid text, just for a test</h1>
<!-- <img src="data:image/jpeg;base64,${img}"/> -->
<div>
<form method = "post" action ="/error-redirect.pcat" enctype="multipart/form-data">
<input type = "text" name = "name">
<input type = "file" name = "pic">
<input type = "submit">
</form>
</div>
</body>
</html>