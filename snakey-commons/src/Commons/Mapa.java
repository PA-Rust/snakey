package Commons;

import java.util.ArrayList;

public class Mapa {
	private ArrayList<Viborita> viboritas;
	private Item item;
	private int ancho;
	private int alto;
	private Entidad[][] grilla;

	public Mapa() {
		this.ancho = 30;
		this.alto = 30;
	}
	
	public Mapa(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public void chequearColisiones() {
		
	}
	
	public void actualizar() {
	}
	
	public void dibujar() {
	}
	
	public Entidad obtenerEntidad(Coordenada posicion) {
		return grilla[posicion.getX()][posicion.getY()];
	}
}
