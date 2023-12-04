package controller;

import java.io.File;
import java.util.ArrayList;

import entities.Ubicacion;
import entities.Ubicaciones;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class ConexionXML {
	
	private JAXBContext context;
	private Ubicaciones ubicaciones;
	private File file;
	
	public ConexionXML() {
		ubicaciones = new Ubicaciones();
		file = new File("C:\\Users\\Luis\\Desktop\\datos.xml");
			try {
				this.context = JAXBContext.newInstance("entities.Ubicaciones");
			} catch (JAXBException e) {
				System.err.println("Error al crear el contexto: " + e.getMessage());
			}
	}

	// Serializar
	public void write(Ubicacion ubicacion) {
		try {
			Unmarshaller unmarshaller = context.createUnmarshaller();
			ubicaciones = (Ubicaciones) unmarshaller.unmarshal(file);
			ubicaciones.getUbicaciones().add(ubicacion);
			
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(ubicaciones, file);
		} catch (JAXBException e) {
			System.err.println("Error en el marshaller: " + e.getMessage());
		}
		

	}
	
	// Deserializar
	public ArrayList<Ubicacion> read() {
		Unmarshaller unmarshaller;
		try {
			unmarshaller = context.createUnmarshaller();
			ubicaciones = (Ubicaciones) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			System.err.println("Error en el marshaller: " + e.getMessage());
		}
		
		return ubicaciones.getUbicaciones();
		
	}
	
}
