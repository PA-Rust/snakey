package Comunicacion.Requests;

import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class IniciarPartidaRequest extends Enviable {
	private static final long serialVersionUID = 4013027630340908873L;
	
	public IniciarPartidaRequest() {
		super(TiposMensaje.START_GAME_REQUEST);
	}
}
