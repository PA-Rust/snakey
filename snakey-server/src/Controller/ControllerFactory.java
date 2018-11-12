package Controller;

import Comunicacion.Enviable;
import Server.ManejadorUsuario;

public class ControllerFactory {
	private ManejadorUsuario manejador;
	
	public ControllerFactory(ManejadorUsuario manejador) {
		this.manejador = manejador;
	}
	
	public Controller manejarEnviables(Enviable enviable) {
		switch (enviable.getTipoMensaje()) {
			case LOGIN_REQUEST:
				return new LoginController(enviable, manejador);
			case REGISTER_REQUEST:
				return new RegisterController(enviable, manejador);
			default:
				throw new RuntimeException("Tipo de mensaje desconocido.");
		}
	}
}
