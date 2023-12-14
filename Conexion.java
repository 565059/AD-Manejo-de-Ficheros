package services;

import java.io.File;
import java.util.ArrayList;

import entities.Ubicacion;

public interface Conexion {

	public static File CSV_FILE = new File("");
	public static File JSON_FILE = new File("");
	public static File XML_FILE = new File("");
	public static File XLS_FILE = new File("");
	
	public ArrayList<Ubicacion> read() throws Exception;
	public void write(Ubicacion ubicacion) throws Exception;
	
}
