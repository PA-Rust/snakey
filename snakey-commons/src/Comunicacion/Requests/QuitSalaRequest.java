package Comunicacion.Requests;

import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class QuitSalaRequest extends Enviable {
	private static final long serialVersionUID = -9011803506862797960L;
	
	public QuitSalaRequest() {
		super(TiposMensaje.QUIT_SALA_REQUEST);
	}
}
