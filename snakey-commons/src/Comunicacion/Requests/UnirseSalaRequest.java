package Comunicacion.Requests;

import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class UnirseSalaRequest extends Enviable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7499289942655822205L;
	private Sala sala;

	public UnirseSalaRequest(Sala sala) {
		super(TiposMensaje.JOIN_SALA_REQUEST);
		this.sala = sala;
	}
	public Sala getSala() {
		return sala;
	}
}
