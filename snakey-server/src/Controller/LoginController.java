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
			return new LoginResponse(existeUsuario, null, "No exite el usuario " + jugador.getNombreDeUsuario());
		}
		
		boolean usuarioValido = daoJugador.claveCorrecta(jugador);
		
		if (usuarioValido) {
			Jugador jugadorDB = daoJugador.getJugador(jugador);
			return new LoginResponse(usuarioValido, jugadorDB, "Inicio de sesion correcto");
		}
		return new LoginResponse(usuarioValido, null, "Nombre de usuario o contraseña incorrecta");
	}
}
