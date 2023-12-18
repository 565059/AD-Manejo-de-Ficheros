<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@page errorPage="error.jsp" %>
		<!DOCTYPE html>
		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>Tratamiento de ficheros</title>
			<link rel="stylesheet" href="styles/acceso.css">
			<script type="text/javascript">
				function validData() {
					const datos = document.getElementsByClassName("datos")
					for (let i = 0; i < datos.length; i++) {
						if (datos[i].value === "") {
							document.getElementById("p_error").innerText = "(*) El campo "
								+ datos[i].name + " no debe estar vacio."
							return false
						}
						if (datos[i].name === "latitud" || datos[i].name === "longitud") {
							if (!isDigit(datos[i])) {
								document.getElementById("p_error").innerText = "(*) El campo "
									+ datos[i].name + " debe ser un numero valido."
								return false
							}
						}
					}
					if (document.getElementById("i_numero").value !== "") {
						if (!isDigit(document.getElementById("i_numero"))) {
							document.getElementById("p_error").innerText = "(*) El campo "
								+ datos[i].name + " debe ser un numero valido."
							return false
						}
					}
					document.getElementById("p_error").innerText = ""
					return true
				}

				function isDigit(num) {
					return !isNaN(parseFloat(num.value))
				}

				function checkData() {
					if (document.getElementById("r_escritura").checked) {
						if (!validData()) {
							return false
						}
					}
				}

				window.onload = function () {
					document.getElementById("r_lectura").onclick = disableElements
					document.getElementById("r_escritura").onclick = enableElements
				}

				function disableElements() {
					const datos = document.getElementsByClassName("datos")
					for (let i = 0; i < datos.length; i++) {
						datos[i].disabled = true
					}
					document.getElementById("i_numero").disabled = true
				}

				function enableElements() {
					const datos = document.getElementsByClassName("datos")
					for (let i = 0; i < datos.length; i++) {
						datos[i].disabled = false
					}
					document.getElementById("i_numero").disabled = false
				}
			</script>
		</head>

		<body>
			<h1>TRATAMIENTO FICHEROS</h1>
			<div class="borde">
				<form id="form" action="ServletAcceso" method="post">
					<table>
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
										<td colspan="2">¿Que quiere hacer con el fichero?</td>
									</tr>
									<tr>
										<td><br></td>
									</tr>
									<tr>
										<td class="center">Lectura:</td>
										<td><input type="radio" name="accion" value="lectura" id="r_lectura"></td>
									</tr>
									<tr>
										<td class="center">Escritura:</td>
										<td><input type="radio" name="accion" value="escritura" id="r_escritura"
												checked></td>
									</tr>
								</table>

							</td>
							<td>
								<table class="right">
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
										<td><input type="text" name="numero" id="i_numero"></td>
									</tr>
									<tr>
										<td>LOCALIDAD:</td>
										<td><input type="text" name="localidad" value="Alcobendas" disabled></td>
									</tr>
									<tr>
										<td>LATITUD:</td>
										<td><input type="text" name="latitud" class="datos"></td>
									</tr>
									<tr>
										<td>LONGITUD:</td>
										<td><input type="text" name="longitud" class="datos"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<div>
						<input type="submit" name="boton" id="b_enviar" value="Enviar" onclick="return checkData();">
					</div>
					<p id="p_error"></p>
				</form>
			</div>
		</body>

		</html>