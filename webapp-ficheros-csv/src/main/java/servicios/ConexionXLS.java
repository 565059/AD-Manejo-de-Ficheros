package servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import entities.Ubicacion;

public class ConexionXLS {

	private File f = new File(
			"H:\\dam2\\AD\\eclipse-workspace\\webapp-ficheros-xls\\src\\main\\resources\\datos\\datos.xls");

	public ArrayList<Ubicacion> read() throws IOException {
		ArrayList<Ubicacion> datos = new ArrayList<Ubicacion>();
		FileInputStream file = new FileInputStream(f);
		// Create Workbook instance holding reference to
		// .xlsx file
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();

		rowIterator.next();

		// Till there is an element condition holds true
		while (rowIterator.hasNext()) {
			Ubicacion ubi = new Ubicacion();
			Row row = rowIterator.next();
			ubi.setNombre(row.getCell(0).getStringCellValue());
			ubi.setDistrito(row.getCell(1).getStringCellValue());
			ubi.setCalle(row.getCell(2).getStringCellValue());
			ubi.setNumero(String.valueOf(row.getCell(3).getNumericCellValue()).substring(0,
					String.valueOf(row.getCell(3).getNumericCellValue()).length() - 2));
			ubi.setLocalidad(row.getCell(4).getStringCellValue());
			ubi.setLatitud(row.getCell(5).getNumericCellValue());
			ubi.setLongitud(row.getCell(6).getNumericCellValue());
			datos.add(ubi);
		}
		workbook.close();
		file.close();
		return datos;
	}

	public void write(Ubicacion ubi) throws IOException {

		ArrayList<Ubicacion> datos = read();
		datos.add(ubi);
		FileInputStream file = new FileInputStream(f);
		// Create Workbook instance holding reference to
		// .xlsx file
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		// Get first/desired sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		int rownum = 0;

		for (Ubicacion u : datos) {
			// Creating a new row in the sheet
			Row row = sheet.createRow(rownum++);
			Cell[] celdas = new Cell[7];
			for (int i = 0; i < 7; i++) {
				celdas[i] = row.createCell(i);
			}
			celdas[0].setCellValue(u.getNombre());
			celdas[1].setCellValue(u.getDistrito());
			celdas[2].setCellValue(u.getCalle());
			celdas[3].setCellValue(u.getNumero().isEmpty() ? 0 : Integer.parseInt(u.getNumero()));
			celdas[4].setCellValue(u.getLocalidad());
			celdas[5].setCellValue(u.getLatitud());
			celdas[6].setCellValue(u.getLongitud());
		}

		// Writing the workbook
		FileOutputStream out = new FileOutputStream(f);
		workbook.write(out);

		// Closing file output connections
		out.close();
		workbook.close();
	}

}
