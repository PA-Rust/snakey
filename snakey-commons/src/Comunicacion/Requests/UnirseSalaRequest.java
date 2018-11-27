package Comunicacion.Requests;

import Commons.Jugador;
import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class UnirseSalaRequest extends Enviable {
	private Jugador jugador;
	private Sala sala;

	public UnirseSalaRequest(Jugador jugador, Sala sala) {
		super(TiposMensaje.JOIN_SALA_REQUEST);
		this.jugador = jugador;
		this.sala = sala;
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public Sala getSala() {
		return sala;
	}
}
