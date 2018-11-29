package Comunicacion.Responses;

import java.util.ArrayList;

import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class GetSalasResponse extends Enviable {
	private static final long serialVersionUID = 8126638301556138445L;
	private ArrayList<Sala> salas;

	public GetSalasResponse(ArrayList<Sala> salas) {
		super(TiposMensaje.GET_SALAS_RESPONSE);
		this.salas = salas;
	}

	public ArrayList<Sala> getSalas() {
		return salas;
	}
}
