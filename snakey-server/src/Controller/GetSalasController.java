package Controller;

import Comunicacion.Enviable;
import Comunicacion.Requests.GetSalasRequest;
import Comunicacion.Responses.GetSalasResponse;
import Server.ManejadorUsuario;

public class GetSalasController implements Controller {
	@SuppressWarnings("unused")
	private GetSalasRequest getSalasRequest;
	private ManejadorUsuario manejadorUsuario;
	
	public GetSalasController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.getSalasRequest = (GetSalasRequest) enviable;
		this.manejadorUsuario = manejadorUsuario;
	}
	
	@Override
	public Enviable manejarMensaje() {
		return new GetSalasResponse(manejadorUsuario.getServerSocket().getSalas());
	}
}
