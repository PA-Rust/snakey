package Controller;

import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.Requests.CrearSalaRequest;
import Comunicacion.Responses.CrearSalaResponse;
import Server.ManejadorUsuario;

public class CrearSalaController implements Controller {
	private CrearSalaRequest crearSalaRequest;
	private ManejadorUsuario manejadorUsuario;
	
	public CrearSalaController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.crearSalaRequest = (CrearSalaRequest) enviable;
		this.manejadorUsuario = manejadorUsuario;
	}
	
	@Override
	public Enviable manejarMensaje() {
		if (manejadorUsuario.getServerSocket().jugadorYaTieneSala(manejadorUsuario.getJugador())) {
			return new CrearSalaResponse(false, null, "El jugador ya tiene una sala creada.");
		}
		
		Sala nuevaSala = manejadorUsuario.getServerSocket().registrarManejadorSala(
			manejadorUsuario,
			crearSalaRequest.getNombreSala(),
			crearSalaRequest.getCantidadJugadores(),
			crearSalaRequest.
		);
		
		if (nuevaSala == null) {
			return new CrearSalaResponse(false, null, "Error al crear la sala.");
		}
		
		return new CrearSalaResponse(true, nuevaSala, "Sala creada correctamente");
	}

}
