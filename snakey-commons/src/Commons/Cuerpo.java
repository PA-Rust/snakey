package Commons;

public class Cuerpo extends Entidad {

	private Direccion direccion;
	
	public Cuerpo(Coordenada posicion) {
		super(posicion);
		this.direccion = Direccion.derecha;
	}
	
	public Cuerpo(Coordenada posicion, Direccion direccion) {
		super(posicion);
		this.direccion = direccion;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}
	
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
}
