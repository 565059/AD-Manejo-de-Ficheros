<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tratamiento de ficheros</title>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("r_lectura").onclick = disableElements
		document.getElementById("r_escritura").onclick = enableElements
	}

	function disableElements() {
		const datos = document.getElementsByClassName("datos")
		for (let i = 0; i < datos.length; i++) {
			datos[i].disabled = true
		}
	}

	function enableElements() {
		const datos = document.getElementsByClassName("datos")
		for (let i = 0; i < datos.length; i++) {
			datos[i].disabled = false
		}
	}
</script>
</head>

<body>
	<h1>TRATAMIENTO FICHEROS</h1>
	<form action="ServletAcceso" method="post">
		<table width="45%">
			<tr>
				<td>
					<table>
						<tr>
							<td>Formato del fichero:</td>
							<td><select name="formato">
									<option value="XLS">XLS</option>
									<option value="CSV">CSV</option>
									<option value="JSON">JSON</option>
									<option value="XML">XML</option>
							</select></td>
						</tr>
						<tr>
							<td colspan="2">
								<hr>
							</td>
						</tr>
						<tr>
							<td colspan="2">¿Qué quiere hacer con el fichero?</td>
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr>
							<td>Lectura:</td>
							<td><input type="radio" name="accion" value="lectura"
								id="r_lectura"></td>
						</tr>
						<tr>
							<td>Escritura:</td>
							<td><input type="radio" name="accion" value="escritura"
								id="r_escritura"></td>
						</tr>
					</table>

				</td>
				<td>
					<table>
						<tr>
							<td>NOMBRE:</td>
							<td><input type="text" name="nombre" class="datos"></td>
						</tr>
						<tr>
							<td>DISTRITO:</td>
							<td><input type="text" name="distrito" class="datos"></td>
						</tr>
						<tr>
							<td>CALLE:</td>
							<td><input type="text" name="calle" class="datos"></td>
						</tr>
						<tr>
							<td>NUMERO:</td>
							<td><input type="text" name="numero" class="datos"></td>
						</tr>
						<tr>
							<td>LOCALIDAD:</td>
							<td><input type="text" name="localidad" class="datos"></td>
						</tr>
						<tr>
							<td>LATITUD:</td>
							<td><textarea name="latitud" class="datos"></textarea></td>
						</tr>
						<tr>
							<td>LONGITUD:</td>
							<td><input type="text" name="longitud" class="datos"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><input type="submit" name="boton" value="Enviar"></td>
				<%
				boolean error = request.getAttribute("error") != null;
				if (error) {
				%>
				<td>
					<!-- aqui hay que añadir para mostrar un texto si hay datos no introducidos -->
					<h4>(*) Los campos no pueden estar vacíos</h4>
				</td>
				<%
				}
				%>
			</tr>
		</table>
	</form>
</body>

</html>