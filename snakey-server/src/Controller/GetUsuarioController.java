package Controller;

import BaseDeDatos.JugadorDao;
import Commons.Jugador;
import Comunicacion.Enviable;
import Comunicacion.Requests.GetProfileRequest;
import Comunicacion.Responses.GetProfileResponse;
import Server.ManejadorUsuario;

public class GetUsuarioController implements Controller {

	private GetProfileRequest getProfileRequest ;
	private ManejadorUsuario manejadorUsuario;
	
	public GetUsuarioController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.getProfileRequest  = (GetProfileRequest ) enviable;
		this.manejadorUsuario = manejadorUsuario;
	}
	
	@Override
	public Enviable manejarMensaje() {
		JugadorDao daoJugador = new JugadorDao();
		Jugador jugador = daoJugador.getJugador(new Jugador(getProfileRequest.getNombreUsuario(),null));
		return new GetProfileResponse(jugador);
	}
}
