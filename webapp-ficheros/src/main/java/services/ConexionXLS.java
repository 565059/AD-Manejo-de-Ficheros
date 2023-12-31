package services;

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

public class ConexionXLS implements Conexion {

	private File f = Files.xlsFile;

	public ArrayList<Ubicacion> read() throws IOException {
		ArrayList<Ubicacion> datos = new ArrayList<Ubicacion>();
		FileInputStream file = new FileInputStream(f);
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		HSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = sheet.iterator();

		rowIterator.next();

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

		HSSFWorkbook workbook = new HSSFWorkbook(file);

		HSSFSheet sheet = workbook.getSheetAt(0);

		int rownum = 0;

		for (Ubicacion u : datos) {
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

		FileOutputStream out = new FileOutputStream(f);
		workbook.write(out);

		out.close();
		workbook.close();
	}

}
