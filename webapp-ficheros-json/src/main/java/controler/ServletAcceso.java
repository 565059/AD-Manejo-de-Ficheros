package controler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.ConexionJson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

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
				case "JSON":
					ConexionJson con = new ConexionJson();

					try {
						datos = con.read();
						page = "resultados.jsp";
					} catch (FileNotFoundException e) {
						request.setAttribute("error", "No se ha encontrado el archivo ");
						page = "error.jsp";

					} catch (IOException e) {
						request.setAttribute("error", "No se ha podido leer el archivo ");
						page = "error.jsp";
						e.printStackTrace();
					} catch (ParseException e) {
						request.setAttribute("error", "Formato JSON invalido");
						page = "error.jsp";
						e.printStackTrace();
					}

					break;
				default:

					break;
				}

			} else {
				if (!request.getParameter("nombre").isEmpty()) {
					String nombre = request.getParameter("nombre");
					String distrito = request.getParameter("distrito");
					String calle = request.getParameter("calle");
					String numero = request.getParameter("numero");
					
					String localidad = request.getParameter("localidad") != null ? request.getParameter("localidad")
							: "Alcobendas";

					Double latitud = !request.getParameter("latitud").isEmpty()
							? Double.parseDouble(request.getParameter("latitud"))
							: null;
					Double longitud = !request.getParameter("longitud").isEmpty()
							? Double.parseDouble(request.getParameter("longitud"))
							: null;
					switch (formato) {
					case "JSON":
						ConexionJson con = new ConexionJson();

						try {
							con.write(new Ubicacion(nombre, distrito, calle, numero, localidad, latitud, longitud));

						} catch (IOException e) {
							request.setAttribute("error", "No se ha  podido escribir el archivo ");
							page = "error.jsp";
						} catch (ParseException e) {
							request.setAttribute("error", "Formato JSON invalido");
							page = "error.jsp";
						}

						try {
							datos = con.read();
							page = "resultados.jsp";
						} catch (FileNotFoundException e) {
							request.setAttribute("error", "No se ha encontrado el archivo ");
							page = "error.jsp";

						} catch (IOException e) {
							request.setAttribute("error", "No se ha podido leer el archivo ");
							page = "error.jsp";
							e.printStackTrace();
						} catch (ParseException e) {
							request.setAttribute("error", "Formato JSON invalido");
							page = "error.jsp";
							e.printStackTrace();
						}

						break;
					default:
						
						break;
					}
				}
			}
			break;
		default:
		}
		request.setAttribute("datos", datos);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
