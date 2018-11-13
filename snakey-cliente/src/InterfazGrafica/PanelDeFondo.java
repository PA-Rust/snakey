package InterfazGrafica;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class PanelDeFondo extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon imagenFondo;
	private String direccion;

	public PanelDeFondo(String rutaImagen) {
		this.direccion = rutaImagen;
	}

	public void paint(Graphics g) {

		imagenFondo = new ImageIcon(getClass().getResource(this.direccion));
		g.drawImage(imagenFondo.getImage(), 0, 0, getSize().width, getSize().height, this);
		setOpaque(false);
		super.paint(g);

	}

}
