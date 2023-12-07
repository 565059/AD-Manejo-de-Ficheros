package services;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import entities.Ubicacion;

public class ConexionXML {

	private File file = new File("F:\\AD-Manejo-de-Ficheros\\webapp-ficheros-xml\\src\\main\\resources");

	public ArrayList<Ubicacion> read() throws Exception{
		ArrayList<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		Document documento = null;
			documento = getDocument();
		if (documento != null) {
			NodeList nodeList = documento.getElementsByTagName("row");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Ubicacion ubicacion = new Ubicacion();
					Element row = (Element) node;
					ubicacion.setNombre(row.getAttribute("Nombre"));
					ubicacion.setDistrito(row.getAttribute("Distrito"));
					ubicacion.setCalle(row.getAttribute("Calle"));
					ubicacion.setNumero(row.getAttribute("Número"));
					ubicacion.setLocalidad(row.getAttribute("Localidad"));
					ubicacion.setLatitud(Double.parseDouble(row.getAttribute("Latitud")));
					ubicacion.setLongitud(Double.parseDouble(row.getAttribute("Longitud")));
					ubicaciones.add(ubicacion);
				}
			}
		} else {
			System.out.println("El documento especificado no se ha encontrado. Revisa la ruta.");
		}

		return ubicaciones;
	}

	public void write(Ubicacion ubicacion) throws Exception {
		ArrayList<Ubicacion> ubicaciones = read();
		ubicaciones.add(ubicacion);
		
	}

	private Document getDocument() throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(file);

	}
}
