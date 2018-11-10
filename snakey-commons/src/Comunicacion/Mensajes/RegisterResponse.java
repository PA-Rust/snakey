package Comunicacion.Mensajes;

import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class RegisterResponse extends Enviable {
	private boolean success;
	private String mensaje;
	
	public RegisterResponse(boolean success, String mensaje) {
		super(TiposMensaje.REGISTER_RESPONSE);
		this.success = success;
		this.mensaje = mensaje;
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
