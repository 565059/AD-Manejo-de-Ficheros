package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import entities.Ubicacion;

public class ConexionJson implements Conexion {
	private File f = new File(
			"C:\\Users\\adrib\\OneDrive\\Escritorio\\Datos\\webapp-ficheros-json\\src\\main\\resources\\datos.json");

	JSONParser parser = new JSONParser();

	public ArrayList<Ubicacion> read() throws FileNotFoundException, IOException, ParseException {
		ArrayList<Ubicacion> datos = new ArrayList<Ubicacion>();

		JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(f));
		for (Object object : jsonArray) {
			JSONObject jsonObject = (JSONObject) object;

			Ubicacion ubicacion = new Ubicacion();
			ubicacion.setNombre((String) jsonObject.get("Nombre"));
			ubicacion.setDistrito((String) jsonObject.get("Distrito"));
			ubicacion.setCalle((String) jsonObject.get("Calle"));
			ubicacion.setNumero((String) jsonObject.get("Número"));
			ubicacion.setLocalidad((String) jsonObject.get("Localidad"));
			ubicacion.setLatitud((Double.parseDouble((String) jsonObject.get("Latitud"))));
			ubicacion.setLongitud((Double.parseDouble((String) jsonObject.get("Longitud"))));
			datos.add(ubicacion);
		}
		return datos;

	}

	public void write(Ubicacion ubicacion) throws IOException, ParseException {
		JSONArray jsonArray;
		if (!f.exists() || f.length() == 0) {
			jsonArray = new JSONArray();
		} else {
			jsonArray = (JSONArray) parser.parse(new FileReader(f));
		}

		JSONObject obj = new JSONObject();
		obj.put("Nombre", ubicacion.getNombre());
		obj.put("Distrito", ubicacion.getDistrito());
		obj.put("Calle", ubicacion.getCalle());
		obj.put("Número", ubicacion.getNumero());
		obj.put("Localidad", ubicacion.getLocalidad());
		obj.put("Latitud", String.valueOf(ubicacion.getLatitud()));
		obj.put("Longitud", String.valueOf(ubicacion.getLongitud()));

		jsonArray.add(obj);

		PrintWriter printWriter = new PrintWriter(new FileWriter(f));

		printWriter.print(jsonArray.toJSONString().replace("\\/", "/"));
		printWriter.close();

	}

}
