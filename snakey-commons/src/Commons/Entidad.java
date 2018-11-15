package Commons;

public class Entidad {
	private Coordenada posicion;
	private Avatar avatar;
	
	public Entidad() {
		this.posicion = new Coordenada(0, 0);
	}
	
	public Entidad(Avatar avatar) {
		this.avatar = avatar;
	}
	
	public Entidad(Coordenada posicion) {
		this.posicion = posicion;
	}
	
	public Entidad(Coordenada posicion, Avatar avatar) {
		this.posicion = posicion;
		this.avatar = avatar;
	}
	
	public void actualizar() {
	}
	
	public void dibujar() {
	}
	
	public Coordenada getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Coordenada nuevaPosicion) {
		this.posicion = nuevaPosicion;
	}
	
	public Avatar getAvatar() {
		return avatar;
	}
	
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	
	public void enColision(Entidad entidad) {
	}
}
