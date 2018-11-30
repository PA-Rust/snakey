package Controller;

import Comunicacion.Enviable;
import Comunicacion.Notifications.InputNotification;
import Server.ManejadorUsuario;

public class InputNotificationController implements Controller {
	private InputNotification inputNotification;
	private ManejadorUsuario manejadorUsuario; 
	
	public InputNotificationController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.inputNotification = (InputNotification) enviable;
		this.manejadorUsuario = manejadorUsuario;
	}
	
	@Override
	public Enviable manejarMensaje() {
		manejadorUsuario.getServerSocket()
			.getManejadorSala(manejadorUsuario.getSalaActual())
			.nuevaInput(manejadorUsuario.getJugador(), inputNotification.getInput());
		return null;
	}
}
