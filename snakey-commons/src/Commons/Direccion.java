package Commons;

import Commons.Input;

public enum Direccion implements Input {
	arriba,
	abajo,
	izquierda,
	derecha;
	
	public String getTipoInput() {
		return "Direccion";
	}
}
