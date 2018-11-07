package Comunicacion.Mensajes;

import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class RegisterRequest extends Enviable {
	private String nombreUsuario;
	private String claveUsuario;

	public RegisterRequest(String nombreUsuario, String claveUsuario) {
		super(TiposMensaje.REGISTER_REQUEST);
		this.nombreUsuario = nombreUsuario;
		this.claveUsuario = claveUsuario;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public String getClaveUsuario() {
		return claveUsuario;
	}
}
