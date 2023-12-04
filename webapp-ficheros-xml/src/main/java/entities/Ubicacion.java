package entities;

import jakarta.xml.bind.annotation.XmlAttribute;

public class Ubicacion {

	private String nombre;
	private String distrito;
	private String calle;
	private String numero;
	private String localidad;
	private Double latitud;
	private Double longitud;

	public Ubicacion() {
		super();
	}

	public Ubicacion(String nombre, String calle, String numero, String localidad, Double latitud, Double longitud) {
		super();
		this.nombre = nombre;
		this.calle = calle;
		this.numero = numero;
		this.localidad = localidad;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	@XmlAttribute(name = "Distrito")
	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	@XmlAttribute(name = "Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@XmlAttribute(name = "Calle")
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@XmlAttribute(name = "Número")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if (numero.equalsIgnoreCase("s/n") || numero.equalsIgnoreCase("0")) {
			numero = "";
		}
		this.numero = numero;
	}

	@XmlAttribute(name = "Localidad")
	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@XmlAttribute(name = "Latitud")
	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	@XmlAttribute(name = "Longitud")
	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public static boolean isDigit(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}

}