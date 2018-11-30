package Controller;

import BaseDeDatos.JugadorDao;
import Commons.Jugador;
import Comunicacion.Enviable;
import Comunicacion.Requests.LoginRequest;
import Comunicacion.Responses.LoginResponse;
import Server.ManejadorUsuario;

public class LoginController implements Controller {
	private LoginRequest loginRequest;
	private ManejadorUsuario manejadorUsuario;
	
	public LoginController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.loginRequest = (LoginRequest)enviable;
		this.manejadorUsuario = manejadorUsuario;
	}

	@Override
	public Enviable manejarMensaje() {
		Jugador jugador = new Jugador(loginRequest.getNombre(), loginRequest.getPassword());
		JugadorDao daoJugador = new JugadorDao();
		boolean existeUsuario = daoJugador.existenciaDeJugador(jugador);
		
		if (!existeUsuario) {
			return new LoginResponse(existeUsuario, null, "No existe el usuario " + jugador.getNombreDeUsuario());
		}

		boolean claveValida = daoJugador.claveCorrecta(jugador);
		
		if (claveValida) {
			Jugador jugadorDB = daoJugador.getJugador(jugador);
			manejadorUsuario.setJugador(jugadorDB);
			return new LoginResponse(claveValida, jugadorDB, "Inicio de sesion correcto");
		}
		return new LoginResponse(claveValida, null, "Contrase�a incorrecta");
	}
}
