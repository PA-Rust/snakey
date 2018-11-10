package Comunicacion.Mensajes;

import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class LoginResponse extends Enviable {
	private boolean success;
	private String mensaje;
	
	public LoginResponse(boolean success, String mensaje) {
		super(TiposMensaje.LOGIN_RESPONSE);
		this.success = success;
	}
	
	public boolean getSuccess() {
		return success;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
