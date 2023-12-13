package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.Conexion;
import services.ConexionJson;
import services.ConexionXLS;
import services.ConexionXML;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

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
	 * @throws IOException
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
			Conexion con = null;
			try {
				switch (formato) {
				case "XLS":
					con = new ConexionXLS();
					break;
				case "XML":
					con = new ConexionXML();
					break;
				case "JSON":
					con = new ConexionJson();
					break;
				case "CSV":
					break;
				default:
					page = "error.jsp";
				}
				if (tipo.equals("escritura")) {
					String nombre = request.getParameter("nombre");
					String distrito = request.getParameter("distrito");
					String calle = request.getParameter("calle");
					String numero = request.getParameter("numero");
					String localidad = "Alcobendas";
					Double latitud = !request.getParameter("latitud").isEmpty()
							? Double.parseDouble(request.getParameter("latitud"))
							: null;
					Double longitud = !request.getParameter("longitud").isEmpty()
							? Double.parseDouble(request.getParameter("longitud"))
							: null;
					if (!page.equals("error.jsp")) {
						con.write(new Ubicacion(nombre, distrito, calle, numero, localidad, latitud, longitud));
						page = "resultados.jsp";
					}
				}
				if (!page.equals("error.jsp")) {
					datos = con.read();
					page = "resultados.jsp";
				}
			} catch (FileNotFoundException ex) {
				request.setAttribute("javax.servlet.jsp.jspException", ex);
				page = "error.jsp";
				ex.printStackTrace();
			} catch (IOException ex) {
				request.setAttribute("javax.servlet.jsp.jspException", ex);
				page = "error.jsp";
				ex.printStackTrace();
			} catch (ParserConfigurationException | ParseException ex) {
				request.setAttribute("javax.servlet.jsp.jspException", ex);
				page = "error.jsp";
				ex.printStackTrace();
			} catch (SAXException | TransformerException e) {
				request.setAttribute("javax.servlet.jsp.jspException", e);
				page = "error.jsp";
				e.printStackTrace();
			} catch (Exception e) {
				request.setAttribute("javax.servlet.jsp.jspException", e);
				page = "error.jsp";
				e.printStackTrace();
			}
			break;
		default:
			page = "error.jsp";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

}
