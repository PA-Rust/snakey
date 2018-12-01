package Comunicacion.Requests;

import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class UnirseSalaRequest extends Enviable {
	private static final long serialVersionUID = -7499289942655822205L;
	private Sala sala;
	private String clave;

	public UnirseSalaRequest(Sala sala, String clave) {
		super(TiposMensaje.JOIN_SALA_REQUEST);
		this.sala = sala;
		this.clave = clave;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public String getClave() {
		return this.clave;
	}
}
