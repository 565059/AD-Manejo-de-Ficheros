<%@page import="java.util.ArrayList"%>
<%@page import="entities.Ubicacion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
ArrayList<Ubicacion> datos = (ArrayList<Ubicacion>) request.getAttribute("datos");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="resultados.css">
</head>
<body>
	<%
	if (datos != null) {
	%>
	<table>
		<tr class="encabezado">
			<td>NOMBRE</td>
			<td>DISTRITO</td>
			<td>CALLE</td>
			<td>NUMERO</td>
			<td>LOCALIDAD</td>
			<td>LATITUD</td>
			<td>LONGITUD</td>
		</tr>
		<%
		for (Ubicacion ubi : datos) {
		%>
		<tr>
			<td><%=ubi.getNombre()%></td>
			<td><%=ubi.getDistrito()%></td>
			<td><%=ubi.getCalle()%></td>
			<td><%=ubi.getNumero()%></td>
			<td><%=ubi.getLocalidad()%></td>
			<td><%=ubi.getLatitud().toString()%></td>
			<td><%=ubi.getLongitud().toString()%></td>
		</tr>
		<%
		}
		%>
	</table>
	<%
	} else {
	%>
	<h2>NO HAY DATOS</h2>
	<%
	}
	%>
	<form action="ServletAcceso" method="post">
		<input type="submit" name="boton" value="Volver">
	</form>
</body>
</html>