package Items;

import Commons.Avatar;
import Commons.Coordenada;
import Viborita.*;

public class Manzana extends Item {
	private static final long serialVersionUID = 3543079481053519320L;

	public Manzana(Coordenada coordenada) {
		super(coordenada, Avatar.MANZANITA);
	}
	
	public Manzana(Coordenada coordenada, int timer) {
		super(coordenada, Avatar.MANZANITA, timer);
	}
	
	@Override
	public void ejecutarAccion(Viborita viborita) {
		viborita.crecer();
	}
}
