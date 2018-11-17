package Viborita;

import Commons.Coordenada;
import Commons.Direccion;
import Commons.Entidad;

public class Cuerpo extends Entidad {

	private Direccion direccion;
	
	public Cuerpo(Coordenada posicion) {
		super(posicion);
		this.direccion = Direccion.derecha;
	}
	
	public Cuerpo(Cuerpo otroCuerpo) {
		super(otroCuerpo.getPosicion());
		this.direccion = otroCuerpo.getDireccion();
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
