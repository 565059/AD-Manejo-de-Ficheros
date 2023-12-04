package entities;

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

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if (numero.equalsIgnoreCase("s/n") || numero.equalsIgnoreCase("0")) {
			numero = "";
		}
		this.numero = numero;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

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
