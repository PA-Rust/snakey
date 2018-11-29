package Comunicacion.Notifications;

import Commons.Partida;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class EstadoPartidaNotification extends Enviable {
	private static final long serialVersionUID = -2315827041971569290L;

	private Partida partida;
	
	public EstadoPartidaNotification(Partida partida) {
		super(TiposMensaje.MENSAJE_PARTIDA);
		this.partida = partida;
	}
	
	public Partida getPartida() {
		return partida;
	}
}
