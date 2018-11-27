package Comunicacion.Responses;

import Commons.Jugador;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class LoginResponse extends Enviable {
	private boolean success;
	private String mensaje;
	private Jugador jugador;
	
	public LoginResponse(boolean success, Jugador jugador, String mensaje) {
		super(TiposMensaje.LOGIN_RESPONSE);
		this.success = success;
		this.jugador = jugador;
	}
	
	public Jugador getJugador() {
		return jugador;
	}

	public boolean getSuccess() {
		return success;
	}
	
	public String getMensaje() {
		return mensaje;
	}
}
