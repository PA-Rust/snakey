package Server;

import Comunicacion.Enviable;

public class ControllerFactory {
	private ManejadorUsuario manejador;
	
	public ControllerFactory(ManejadorUsuario manejador) {
		this.manejador = manejador;
	}
	
	public Controller manejarEnviables(Enviable enviable) {
		switch (enviable.getTipoMensaje()) {
			case LOGIN_REQUEST:
				return new LoginController(enviable, manejador);
			default:
				throw new RuntimeException("Tipo de mensaje desconocido.");
		}
	}
}
