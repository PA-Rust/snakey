package Items;

import Commons.Avatar;
import Commons.Coordenada;
import Commons.Entidad;
import Viborita.Viborita;

public abstract class Item extends Entidad {
	private int reloj;
	
	public Item(Coordenada coordenada) {
		super(coordenada);
		reloj = 5000;
	}
	
	public Item(Coordenada coordenada, Avatar avatar) {
		super(coordenada, avatar);
		reloj = 5000;
	}
	
	public Item(Coordenada coordenada, Avatar avatar, int reloj) {
		super(coordenada, avatar);
		this.reloj = reloj;
	}
	
	public int getReloj() {
		return reloj;
	}
	
	public void decrementarReloj(int cantidad) {
		reloj -= cantidad;
	}
	
	@Override
	public void enColision(Entidad entidad) {
		reloj = 0;
		if (entidad instanceof Viborita) {
			Viborita viborita = (Viborita)entidad;
			ejecutarAccion(viborita);
		}
	}
	
	public abstract void ejecutarAccion(Viborita viborita);
}
