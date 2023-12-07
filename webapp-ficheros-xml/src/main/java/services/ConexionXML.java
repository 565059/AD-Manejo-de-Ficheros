package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entities.Ubicacion;

public class ConexionXML {

	private File file = new File("F:\\AD-Manejo-de-Ficheros\\webapp-ficheros-xml\\src\\main\\resources\\datos.xml");

	// Leectura XML
	public ArrayList<Ubicacion> read() throws IOException, ParserConfigurationException, SAXException {
		ArrayList<Ubicacion> ubies = new ArrayList<Ubicacion>();
		Document doc = getDocument();
		
		// Recoge los nodos row y los añade al ArrayList
		NodeList nodes = doc.getElementsByTagName("row");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Ubicacion ubi = new Ubicacion();
				Element row = (Element) node;
				ubi.setNombre(row.getAttribute("Nombre"));
				ubi.setDistrito(row.getAttribute("Distrito"));
				ubi.setCalle(row.getAttribute("Calle"));
				ubi.setNumero(row.getAttribute("Número"));
				ubi.setLocalidad(row.getAttribute("Localidad"));
				ubi.setLatitud(Double.parseDouble(row.getAttribute("Latitud")));
				ubi.setLongitud(Double.parseDouble(row.getAttribute("Longitud")));
				ubies.add(ubi);
			}
		}
		return ubies;
	}

	// Escritura XML
	public void write(Ubicacion ubi)
			throws IOException, ParserConfigurationException, SAXException, TransformerException {
		Document doc = getDocument();

		// Crea un nuevo elemento row a partir de la ubicacion proporcionada
		Element row = doc.createElement("row");
		row.setAttribute("Nombre", ubi.getNombre());
		row.setAttribute("Distrito", ubi.getDistrito());
		row.setAttribute("Calle", ubi.getCalle());
		row.setAttribute("Número", ubi.getNumero());
		row.setAttribute("Localidad", ubi.getLocalidad());
		row.setAttribute("Latitud", String.valueOf(ubi.getLatitud()));
		row.setAttribute("Longitud", String.valueOf(ubi.getLongitud()));
		doc.getDocumentElement().appendChild(row);

		FileOutputStream output = new FileOutputStream(file);
		transformDocument(doc, output);

	}

	// Devuelve un documento a partir de un archivo
	private Document getDocument() throws IOException, ParserConfigurationException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(file);
	}

	// Transforma un documento a un resultado
	private void transformDocument(Document doc, FileOutputStream output) throws TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(output);
		transformer.transform(source, result);
	}

}
