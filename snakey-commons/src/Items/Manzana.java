package Items;

import Commons.Coordenada;
import Viborita.*;

public class Manzana extends Item {
	public Manzana(Coordenada coordenada) {
		super(coordenada);
	}
	
	public Manzana(Coordenada coordenada, int timer) {
		super(coordenada, timer);
	}
	@Override
	public void ejecutarAccion(Viborita viborita) {
		viborita.crecer();
	}
}
