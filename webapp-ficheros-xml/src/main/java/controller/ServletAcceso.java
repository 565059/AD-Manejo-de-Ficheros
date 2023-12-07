package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import servicios.ConexionXLS;
import services.ConexionXML;

import java.io.IOException;
import java.util.ArrayList;

import entities.Ubicacion;

/**
 * Servlet implementation class ServletAcceso
 */
public class ServletAcceso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAcceso() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String boton = request.getParameter("boton");
		String page = "";
		ArrayList<Ubicacion> datos = null;
		switch (boton) {
		case "Volver":
			page = "acceso.jsp";
			break;
		case "Enviar":
			String formato = request.getParameter("formato");
			String tipo = request.getParameter("accion");
			if (tipo.equals("lectura")) {
				switch (formato) {
				case "XLS":
					/*
					 * ConexionXLS con = new ConexionXLS(); datos = con.read();
					 */
					break;
				case "XML":
					ConexionXML con = new ConexionXML();
					try {
						datos = con.read();
						page = "resultados.jsp";
					} catch (Exception e) {
						request.setAttribute("error", "Archivo no encontrado. Revisa la ruta.");
						page = "error.jsp";
					}

					break;
				default:
					// no controlado;
					break;
				}

			} else {
				if (!request.getParameter("nombre").isEmpty()) {
					switch (formato) {
					case "XLS":
						/*
						 * ConexionXLS con = new ConexionXLS(); con.write(new Ubicacion()); datos =
						 * con.read();
						 */
						break;
					case "XML":
						ConexionXML con = new ConexionXML();
						try {
							con.write(new Ubicacion());
							datos = con.read();
							page = "resultados.jsp";
						} catch (Exception e) {
							request.setAttribute("error", "Archivo no encontrado. Revisa la ruta.");
							page = "error.jsp";
						}

						break;

					default:
						// no controlado;
						break;
					}
					page = "resultados.jsp";
				}
			}
			break;
		default:
		}
		request.setAttribute("datos", datos);
		request.getRequestDispatcher(page).forward(request, response);
	}

}