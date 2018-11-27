package Comunicacion.Responses;

import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class CrearSalaResponse extends Enviable {
	private boolean success;
	private Sala sala;
	private String message;
	
	public CrearSalaResponse(boolean success, Sala sala, String message) {
		super(TiposMensaje.CREATE_SALA_RESPONSE);
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
	
	public String message() {
		return message;
	}
}
