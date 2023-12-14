<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Página de error</title>
</head>
<body>
	<h2 style="color: red;"><%=exception.getMessage() %></h2>
	<hr>
	<form action="acceso.jsp" method="post">
		<input type="submit" name="boton" value="Volver">
	</form>
</body>
</html>