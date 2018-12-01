package Controller;

import Commons.Sala;
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
		String claveSala = unirseSalaRequest.getSala().getClaveSala();
		String claveIngresada = unirseSalaRequest.getClave();
		if (manejadorSala == null) {
			return new UnirseSalaResponse(false, null, "Sala no existente.");
		}
		
		if (manejadorSala.getSala().getSalaLlena()) {
			return new UnirseSalaResponse(false, null, "Sala llena.");
		}
		
		if(claveSala.trim().length()!=0 && claveSala.equals(claveIngresada))
			return new UnirseSalaResponse(false, null, "Clave invalida.");
		
		manejadorSala.unirNuevoUsuario(manejadorUsuario);
		Sala sala = manejadorSala.getSala();
		return new UnirseSalaResponse(true, sala, "Se ha unido a la sala exitosamente");
	}

}
