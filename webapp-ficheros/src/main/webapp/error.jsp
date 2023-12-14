<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>P�gina de error</title>
<link rel="stylesheet" href="styles/error.css">
</head>
<body>
	<hr>
	<h2>HA OCURRIDO UN ERROR</h2>
	<hr>
	<p><%=exception.getMessage()%></p>
	<br>
	<details>
	<summary>Mostrar m�s informaci�n</summary>
	<%String error = "";
		for(StackTraceElement ste: exception.getStackTrace()){
			error += ste.toString() + "\n";
		}
		%>
	<p id="info"><%=error %><p>
	</details>
	<br>
	<form action="acceso.jsp" method="post">
		<input type="submit" name="boton" value="Volver">
	</form>
</body>
</html>