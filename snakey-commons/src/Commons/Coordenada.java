package Commons;

import java.io.Serializable;

public class Coordenada implements Serializable {
	private static final long serialVersionUID = 4054280452238525600L;
	private int x = 0;
	private int y = 0;
	
	public Coordenada() {
	}
	
	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void desplazar(Coordenada desplazamiento) {
		this.x += desplazamiento.x;
		this.y += desplazamiento.y;
	}
	
	// Esto lo que hara sera que si la coordenada en el eje X o Y
	// se encuentra menor que 0, deberia ser la coordenada maxima del
	// otro lado del mapa. Y al reves en el caso de ser mayor que la coordenada
	// maxima en alguno de los ejes.
	public Coordenada warp(Coordenada coordenadaMaxima) {
		if (x < 0) {
			x = coordenadaMaxima.x - 1;
		} else if (x >= coordenadaMaxima.x) {
			x = 0;
		}
		
		if (y < 0) {
			y = coordenadaMaxima.y - 1;
		} else if (y >= coordenadaMaxima.y) {
			y = 0;
		}
		
		return this;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Coordenada)) {
			return false;
		}
		Coordenada coord = (Coordenada)other;
		return x == coord.x && y == coord.y;
	}
}
