package Controller;

import java.io.IOException;

import Comunicacion.Enviable;
import Comunicacion.Requests.IniciarPartidaRequest;
import Server.ManejadorSala;
import Server.ManejadorUsuario;

public class IniciarPartidaController implements Controller {
	private IniciarPartidaRequest iniciarPartidaRequest;
	private ManejadorUsuario manejadorUsuario;
	
	public IniciarPartidaController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.iniciarPartidaRequest = (IniciarPartidaRequest) enviable;
		this.manejadorUsuario = manejadorUsuario;
	}

	@Override
	public Enviable manejarMensaje() {
		if (!manejadorUsuario.getSalaActual().getJugadorPropietario().equals(manejadorUsuario.getJugador())) {
			return null;
		}
		try {
			ManejadorSala manejadorDeSala = manejadorUsuario.getServerSocket()
				.getManejadorSala(manejadorUsuario.getSalaActual());
			manejadorDeSala.iniciarNuevaPartida();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
