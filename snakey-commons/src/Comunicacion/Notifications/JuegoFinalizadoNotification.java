package Comunicacion.Notifications;

import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class JuegoFinalizadoNotification extends Enviable {
	private static final long serialVersionUID = -6392579265431177362L;

	public JuegoFinalizadoNotification() {
		super(TiposMensaje.GAME_FINISHED);
	}

}
