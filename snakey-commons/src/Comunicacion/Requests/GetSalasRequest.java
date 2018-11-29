package Comunicacion.Requests;

import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class GetSalasRequest extends Enviable {
	private static final long serialVersionUID = -5933165788631511280L;

	public GetSalasRequest() {
		super(TiposMensaje.GET_SALAS_REQUEST);
	}
}
