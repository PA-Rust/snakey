package Controller;

import Comunicacion.Enviable;
import Comunicacion.Requests.UnirseSalaRequest;
import Comunicacion.Responses.UnirseSalaResponse;
import Server.ManejadorSala;
import Server.ManejadorUsuario;

public class UnirseSalaController implements Controller {
	private UnirseSalaRequest unirseSalaRequest;
	private ManejadorUsuario manejadorUsuario;
	
	public UnirseSalaController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.unirseSalaRequest = (UnirseSalaRequest) enviable;
		this.manejadorUsuario = manejadorUsuario;
	}

	@Override
	public Enviable manejarMensaje() {
		ManejadorSala manejadorSala = manejadorUsuario.getServerSocket()
            .getManejadorSala(unirseSalaRequest.getSala());
		System.out.println(manejadorSala == null ? "Sala null" : "Sala conseguida");
		if (manejadorSala == null) {
			return new UnirseSalaResponse(false, null, "Sala no existente.");
		}
		if (manejadorSala.getSala().getSalaLlena()) {
			return new UnirseSalaResponse(false, null, "Sala llena.");
		}
		manejadorSala.unirNuevoUsuario(manejadorUsuario);
		return new UnirseSalaResponse(true, manejadorSala.getSala(), "Se ha unido a la sala exitosamente");
	}

}
