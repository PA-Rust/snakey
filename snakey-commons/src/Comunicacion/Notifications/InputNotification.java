package Comunicacion.Notifications;

import Commons.Input;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class InputNotification extends Enviable {
	private static final long serialVersionUID = -3115187569101553102L;
	
	private Input input;

	public InputNotification(Input input) {
		super(TiposMensaje.MENSAJE_INPUTS);
		this.input = input;
	}
	
	public Input getInput() {
		return input;
	}
}
