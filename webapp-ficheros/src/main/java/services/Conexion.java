package services;

import java.util.ArrayList;

import entities.Ubicacion;

public interface Conexion {
	
	public ArrayList<Ubicacion> read() throws Exception;
	public void write(Ubicacion ubicacion) throws Exception;
	
}
