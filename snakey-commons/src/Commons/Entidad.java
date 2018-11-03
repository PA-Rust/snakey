package Commons;

public class Entidad {
	private Coordenada posicion;
	private String sprite;
	
	public Entidad() {
		this.posicion = new Coordenada(0, 0);
	}
	
	public Entidad(Coordenada posicion) {
		this.posicion = posicion;
	}
	
	public void actualizar() {
	}
	
	public void dibujar() {
	}
	
	public Coordenada obtenerPosicion() {
		return posicion;
	}
	
	public String getSprite() {
		return sprite;
	}
	
	public void enColision(Entidad entidad) {
	}
}
