package Controller;

import Comunicacion.Enviable;
import Comunicacion.Requests.QuitSalaRequest;
import Server.ManejadorUsuario;

public class QuitSalaController implements Controller {
	private QuitSalaRequest quitSalaRequest;
	private ManejadorUsuario manejadorUsuario;
	
	public QuitSalaController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.quitSalaRequest = (QuitSalaRequest) enviable;
		this.manejadorUsuario = manejadorUsuario;
	}

	@Override
	public Enviable manejarMensaje() {
		manejadorUsuario.getServerSocket()
			.getManejadorSala(quitSalaRequest.getSala())
			.removeListener(manejadorUsuario);
		return null;
	}
}
