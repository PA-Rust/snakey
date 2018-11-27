package Comunicacion.Mensajes;

import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class UnirseSalaResponse extends Enviable {
	private boolean success;
	private Sala sala;
	private String message;
	
	public UnirseSalaResponse(boolean success, Sala sala, String message) {
		super(TiposMensaje.JOIN_SALA_RESPONSE);
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public Sala getSala() {
		return sala;
	}

	public String getMessage() {
		return message;
	}

}
