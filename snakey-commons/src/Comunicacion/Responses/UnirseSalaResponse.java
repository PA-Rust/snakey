package Comunicacion.Responses;

import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class UnirseSalaResponse extends Enviable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1893207883161770948L;
	private boolean success;
	private Sala sala;
	private String message;
	
	public UnirseSalaResponse(boolean success, Sala sala, String message) {
		super(TiposMensaje.JOIN_SALA_RESPONSE);
		this.success = success;
		this.sala = sala;
		this.message = message;
	}

	public boolean getSuccess() {
		return success;
	}

	public Sala getSala() {
		return sala;
	}

	public String getMessage() {
		return message;
	}

}
