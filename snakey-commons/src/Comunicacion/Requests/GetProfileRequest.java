package Comunicacion.Requests;

import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class GetProfileRequest extends Enviable {
	String nombreUsuario;
	public GetProfileRequest(String nombreUsuario) {
		super(TiposMensaje.GET_USUARIO_REQUEST);
		this.nombreUsuario = nombreUsuario;
	}
public String getNombreUsuario() {
	return this.nombreUsuario;
	
}

}
