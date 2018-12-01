package Comunicacion.Responses;

import Commons.Jugador;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class GetProfileResponse extends Enviable {
	private Jugador jugador;
	public GetProfileResponse(Jugador jugador) {
		super(TiposMensaje.GET_USUARIO_REQUEST);
		this.jugador = jugador;
	}

	public Jugador getJugador() {
		return this.jugador;
	}

}
