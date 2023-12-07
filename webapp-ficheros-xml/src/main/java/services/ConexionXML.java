package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import entities.Ubicacion;

public class ConexionXML {

	private File file = new File("F:\\AD-Manejo-de-Ficheros\\webapp-ficheros-xml\\src\\main\\resources\\datos.xml");

	public ArrayList<Ubicacion> read() {
		ArrayList<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		Document doc = null;
		try {
			doc = getDocument();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (doc != null) {
			NodeList nodeList = doc.getElementsByTagName("row");

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
			System.out.println("El doc especificado no se ha encontrado. Revisa la ruta.");
		}

		return ubicaciones;
	}

	public void write(Ubicacion ubi) {
		Document doc = null;
		try {
			doc = getDocument();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element row = doc.createElement("row");
		row.setAttribute("Nombre", ubi.getNombre());
		row.setAttribute("Distrito", ubi.getDistrito());
		row.setAttribute("Calle", ubi.getCalle());
		row.setAttribute("Número", ubi.getNumero());
		row.setAttribute("Localidad", ubi.getLocalidad());
		row.setAttribute("Latitud", String.valueOf(ubi.getLatitud()));
		row.setAttribute("Longitud", String.valueOf(ubi.getLongitud()));
		doc.getDocumentElement().appendChild(row);

		try (FileOutputStream output = new FileOutputStream(file)) {
			writeXML(doc, output);
		} catch (IOException | TransformerException e) {
			e.printStackTrace();
		}

	}
	
	private Document getDocument() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(file);
	}
	
	private void writeXML(Document doc, FileOutputStream output) throws TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(output);
		transformer.transform(source, result);
	}
	
}
