package Comunicacion.Mensajes;

import Commons.Partida;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class PartidaIniciada extends Enviable {
	private Partida partida;
	
	public PartidaIniciada(Partida partida) {
		super(TiposMensaje.GAME_STARTED);
		this.partida = partida;
	}

	public Partida getPartida() {
		return partida;
	}
}
