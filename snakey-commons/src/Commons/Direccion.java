package Commons;

import java.io.Serializable;

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
