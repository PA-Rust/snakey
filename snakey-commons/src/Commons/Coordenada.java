package Commons;

public class Coordenada {

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

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Coordenada)) {
			return false;
		}
		Coordenada coord = (Coordenada)other;
		return x == coord.x && y == coord.y;
	}
}
