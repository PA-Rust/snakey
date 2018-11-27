package Comunicacion.Mensajes;

import Commons.Jugador;
import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class IniciarPartidaRequest extends Enviable {
	private Sala sala;
	private Jugador jugador;
	
	public IniciarPartidaRequest(Sala sala, Jugador jugador) {
		super(TiposMensaje.START_GAME_REQUEST);
		this.sala = sala;
		this.jugador = jugador;
	}

	public Sala getSala() {
		return sala;
	}

	public Jugador getJugador() {
		return jugador;
	}
}
