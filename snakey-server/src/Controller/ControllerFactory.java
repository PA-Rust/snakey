package Controller;

import Comunicacion.Enviable;
import Server.ManejadorUsuario;

public class ControllerFactory {
	private ManejadorUsuario manejador;
	// private ManejadorServidor manejadorServidor;
	
	public ControllerFactory(ManejadorUsuario manejador) {
		this.manejador = manejador;
	}
	
	public ControllerFactory(/*ManejadorServidor manejadorServidor*/) {
		// this.manejadorServidor = manejadorServidor;
	}
	
	public Controller manejarEnviables(Enviable enviable) {
		switch (enviable.getTipoMensaje()) {
			case LOGIN_REQUEST:
				return new LoginController(enviable, manejador);
			default:
				throw new RuntimeException("Tipo de mensaje desconocido.");
		}
	}
	
	public Controller manerEnviableResponse(Enviable enviable) {
		switch (enviable.getTipoMensaje()) {
			case LOGIN_RESPONSE:
				return new LoginController(enviable, null);
			default:
				throw new RuntimeException("Tipo de mensaje desconocido");
		}
	}
}
