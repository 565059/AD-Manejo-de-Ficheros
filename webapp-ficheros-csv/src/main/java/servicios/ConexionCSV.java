package servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import entities.Ubicacion;

public class ConexionCSV {

	private File file = new File("D:\\datos.csv");

	public ArrayList<Ubicacion> read() throws CsvValidationException, NumberFormatException, IOException {
		ArrayList<Ubicacion> datos = new ArrayList<Ubicacion>();
		CSVReader reader = new CSVReader(new FileReader(file));
		String[] nextLine;

		// Sacar los títulos del CSV
		reader.readNext();

		// Lectura de las lineas del CSV
		while ((nextLine = reader.readNext()) != null) {
			Ubicacion ubicacion = new Ubicacion();
			ubicacion.setNombre(nextLine[0]);
			ubicacion.setDistrito(nextLine[1]);
			ubicacion.setCalle(nextLine[2]);
			ubicacion.setNumero(nextLine[3]);
			ubicacion.setLocalidad(nextLine[4]);
			ubicacion.setLatitud(Double.valueOf(nextLine[5]));
			ubicacion.setLongitud(Double.valueOf(nextLine[6]));
			datos.add(ubicacion);
		}

		reader.close();
		return datos;
	}

	public void write(Ubicacion ubicacion) throws IOException, CsvValidationException, NumberFormatException {

		ArrayList<Ubicacion> datos = read();
		datos.add(ubicacion);
		
		
		CSVWriter writer = new CSVWriter(new FileWriter(file, true));
        
		String[] datoPendiente = {
				ubicacion.getNombre(),
				ubicacion.getDistrito(),
				ubicacion.getCalle(),
				ubicacion.getNumero(),
				ubicacion.getLocalidad(),
				String.valueOf(ubicacion.getLatitud()),
				String.valueOf(ubicacion.getLongitud())
				};

        
        
        writer.writeNext(datoPendiente, false);
		
		writer.close();
		return;
	}

}
