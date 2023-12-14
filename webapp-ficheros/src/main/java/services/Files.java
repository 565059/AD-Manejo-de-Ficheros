package services;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Files {
	
	//Codigos de los tipos de archivo
	public static final int CSV_CODE = 0;
	public static final int XML_CODE = 1;
	public static final int JSON_CODE = 2;
	public static final int XLS_CODE = 3;
	
	//las extensiones
	public static final String[] FILE_EXTENSIONS = {".csv", ".xml", ".json", ".xls"};
	
	//la url de donde sacamos el archivo
	public static URL[] getUrls() {
		URL[] urls = new URL[4];
		try {
			for(int i = 0; i < urls.length; i++) {
			urls[CSV_CODE] = new URL("https://datos.alcobendas.org/dataset/5fb50fff-d42c-406e-a183-99865924209b/resource/ed7b0722-ae29-491e-9eab-0549e0963aa2/download/desfibriladores-en-alcobendas.csv");
			urls[XML_CODE] = new URL("https://datos.alcobendas.org/dataset/5fb50fff-d42c-406e-a183-99865924209b/resource/78bb457b-634d-452f-90d3-89af18d4907d/download/desfibriladores-en-alcobendas.xml");
			urls[JSON_CODE] = new URL("https://datos.alcobendas.org/dataset/5fb50fff-d42c-406e-a183-99865924209b/resource/4b7def51-6445-472e-8f26-e490f1fbfb4e/download/desfibriladores-en-alcobendas.json");
			urls[XLS_CODE] = new URL("https://datos.alcobendas.org/dataset/5fb50fff-d42c-406e-a183-99865924209b/resource/18056449-cae2-45c1-b96b-6d4b62b28ad4/download/desfibriladores-en-alcobendas.xls");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return urls;
	}

	//los archivos que van a utilizar para escritura y lectura
	public static File csvFile = null;
	public static File xmlFile = null;
	public static File jsonFile = null;
	public static File xlsFile = null;
	
	//con este metodo se actualiza el archivo seleccionado con el código
	public static void updateFiles(File fileGuardar, int code) {
		switch (code) {
		case CSV_CODE:
			csvFile = fileGuardar;
			break;
		case XML_CODE:
			xmlFile = fileGuardar;
			break;
		case JSON_CODE:
			jsonFile = fileGuardar;
			break;
		case XLS_CODE:
			xlsFile = fileGuardar;
			break;
		default:
			break;
		}
	}
	
}
