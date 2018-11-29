package Comunicacion.Notifications;

import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class CambioSalaNotification extends Enviable {
	private static final long serialVersionUID = -4169322756619380199L;
	private Sala sala;
	
	public CambioSalaNotification(Sala salaActualizada) {
		super(TiposMensaje.NOTIFICACION_CAMBIO_SALA);
		this.sala = salaActualizada;
	}
	
	public Sala getSala() {
		return sala;
	}
}
