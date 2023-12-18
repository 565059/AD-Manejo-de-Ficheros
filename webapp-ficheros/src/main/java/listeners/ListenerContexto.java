package listeners;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import services.Files;

/**
 * Application Lifecycle Listener implementation class ListenerContexto
 *
 */
public class ListenerContexto implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public ListenerContexto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		URL[] urls = Files.getUrls();
		for (int i = 0; i<4; i++) {
			try {
				URL url = urls[i];
				URLConnection urlCon = url.openConnection();

				InputStream is = urlCon.getInputStream();
				String path = sce.getServletContext().getRealPath("/") + "datos" + Files.FILE_EXTENSIONS[i];
				FileOutputStream fos = new FileOutputStream(path);
				
				//lectura y escritura
				byte[] array = new byte[1000]; // buffer temporal de lectura.
				int leido = is.read(array);
				while (leido > 0) {
					fos.write(array, 0, leido);
					leido = is.read(array);
				}
				
				//se actualiza el archivo estatico
				File fileGuardar = new File(path);
				Files.updateFiles(fileGuardar, i);
				
				is.close();
				fos.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
