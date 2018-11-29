package Comunicacion.Requests;

import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class QuitSalaRequest extends Enviable {
	private static final long serialVersionUID = -9011803506862797960L;
	private Sala sala;
	
	public QuitSalaRequest(Sala sala) {
		super(TiposMensaje.QUIT_SALA_REQUEST);
		this.sala = sala;
	}
	
	public Sala getSala() {
		return sala;
	}
}
