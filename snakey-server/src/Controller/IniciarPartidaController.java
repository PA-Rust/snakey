package Controller;

import Comunicacion.Enviable;
import Comunicacion.Mensajes.IniciarPartidaRequest;
import Server.ManejadorUsuario;

public class IniciarPartidaController implements Controller {
	private IniciarPartidaRequest iniciarPartidaRequest;
	private ManejadorUsuario manejadorUsuario;
	
	public IniciarPartidaController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.iniciarPartidaRequest = (IniciarPartidaRequest) enviable;
		this.manejadorUsuario = manejadorUsuario;
	}

	@Override
	public Enviable manejarMensaje() {
		
		return null;
	}

}
