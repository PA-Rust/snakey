package Comunicacion.Requests;

import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class LoginRequest extends Enviable {
	private String nombre;
	private String password;
	
	public LoginRequest(String nombre, String password) {
		super(TiposMensaje.LOGIN_REQUEST);
		this.nombre = nombre;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}
}
