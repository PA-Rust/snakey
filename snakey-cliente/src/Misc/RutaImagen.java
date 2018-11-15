package Misc;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class RutaImagen {
	public static Image get(final String pathAndFileName) {
		System.out.println(pathAndFileName);
        final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
        if (url == null)
            System.out.println("Imagen nula!");
        return Toolkit.getDefaultToolkit().getImage(url);
    }
}
