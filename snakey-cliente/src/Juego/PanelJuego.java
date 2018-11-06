package Juego;

import javax.swing.JPanel;
import java.awt.Graphics;

import Commons.Mapa;
import Commons.Partida;

public class PanelJuego extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Partida partida;
	
	public PanelJuego(Partida partida) {
		this.partida = partida;
	}
	
	public Partida getPartida() {
		return partida;
	}
	
	public void paintComponent(Graphics graphics) {
		
	}
	
	public void actualizar() {
		
	}
}
