package entities;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class Ubicaciones {

	Ubicacion u;
	
	private ArrayList<Ubicacion> ubicaciones;

	@XmlElement(name = "row")
	public ArrayList<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(ArrayList<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	
}
