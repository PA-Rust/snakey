package Comunicacion.Notifications;

import Commons.Partida;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class JuegoIniciadoNotification extends Enviable {
	private static final long serialVersionUID = 9219535306119377363L;
	private Partida partida;
	
	public JuegoIniciadoNotification(Partida partida) {
		super(TiposMensaje.GAME_STARTED);
		this.partida = partida;
	}

	public Partida getPartida() {
		return partida;
	}
}
