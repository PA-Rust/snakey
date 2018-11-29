package Controller;

import BaseDeDatos.JugadorDao;
import Commons.Jugador;
import Comunicacion.Enviable;
import Comunicacion.Requests.RegisterRequest;
import Comunicacion.Responses.RegisterResponse;
import Server.ManejadorUsuario;

public class RegisterController implements Controller {
	private RegisterRequest registerRequest;
	private ManejadorUsuario manejadorUsuario;
	
	public RegisterController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.registerRequest = (RegisterRequest) enviable;
		this.manejadorUsuario = manejadorUsuario;
	}
	
	@Override
	public Enviable manejarMensaje() {
		String mensajeExito = "Registro exitoso";
		String mensajeFallaPorExistencia = "Registro no valido,usuario ya existente";
		JugadorDao daoJugador = new JugadorDao();
		Jugador jugador = new Jugador(registerRequest.getNombreUsuario(),registerRequest.getClaveUsuario());
		boolean usuarioRegistrado = daoJugador.insertarEnBdd(jugador);
		
		if(usuarioRegistrado)
			return new RegisterResponse(usuarioRegistrado, mensajeExito);
		return new RegisterResponse(usuarioRegistrado, mensajeFallaPorExistencia ); 
	}
}
