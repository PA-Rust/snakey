package Comunicacion.Notifications;

import Commons.Input;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class InputNotification extends Enviable {
	private static final long serialVersionUID = -3115187569101553102L;

	public InputNotification(Input input) {
		super(TiposMensaje.MENSAJE_INPUTS);
	}
}
