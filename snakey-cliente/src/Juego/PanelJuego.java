package Juego;

import javax.swing.JPanel;

import Commons.Mapa;
import Commons.Partida;

public class PanelJuego extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Partida partida;
	private Mapa mapa;
	
	public PanelJuego(Partida partida) {
		this.partida = partida;
		this.mapa = partida.getMapa();
	}
	
	public void dibujar() {
		mapa.dibujar();
	}
	
	public void actualizar() {
		
	}
	
	public static void main(String[] args) {
		
	}
}
