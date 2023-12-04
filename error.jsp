<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2 style="color:red;">ERROR</h2>
	<hr>
	<%
	String error = request.getAttribute("error") != null ? (String) request.getAttribute("error") : "ERROR POR DEFECTO";
	%>
	<p><%=error %></p>
	<form action="ServletAcceso" method="post">
		<input type="submit" name="boton" value="Volver">
	</form>
</body>
</html>