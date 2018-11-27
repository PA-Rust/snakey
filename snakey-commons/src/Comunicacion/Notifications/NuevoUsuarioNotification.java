package Comunicacion.Notifications;

import Commons.Jugador;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class NuevoUsuarioNotification extends Enviable {
	private Jugador jugador;
	
	public NuevoUsuarioNotification(Jugador nuevoJugador) {
		super(TiposMensaje.USUARIO_NUEVO_EN_SALA);
		this.jugador = nuevoJugador;
	}

	public Jugador getJugador() {
		return jugador;
	}
}
