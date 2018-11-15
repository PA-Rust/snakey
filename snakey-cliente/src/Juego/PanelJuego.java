package Juego;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

// import Commons.Mapa;
import Commons.Partida;
import Misc.RutaImagen;
import Commons.Avatar;

public class PanelJuego extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Partida partida;
	private Map<Avatar, Image> imagenes;
	
	public PanelJuego(Partida partida) {
		imagenes = new HashMap<Avatar, Image>();
		for (Avatar avatar: Avatar.values()) {
			imagenes.put(avatar, new ImageIcon(RutaImagen.get("sprites/" + avatar.getSprite())).getImage());
		}
		this.partida = partida;
		ejecutarTimer();
	}
	
	public Partida getPartida() {
		return partida;
	}
	
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		actualizar(graphics);
	}
	
	public void actualizar(Graphics g) {
		partida.getMapa().actualizar();
		int blockSize = this.getSize().width / partida.getMapa().getAncho();
		partida.getMapa().dibujar(g, imagenes, 600, blockSize);
		// No server logic -\
	}
	
	public void ejecutarTimer() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
		    @Override
		    public void run() {
		        repaint();
		    }
		}, 0, 60);
	}
}
