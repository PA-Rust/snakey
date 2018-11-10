package Server;

import Commons.Jugador;
import Comunicacion.Enviable;
import Comunicacion.Mensajes.LoginRequest;
import Comunicacion.Mensajes.LoginResponse;

public class LoginController implements Controller {
	private LoginRequest loginRequest;
	private ManejadorUsuario manejadorUsuario;
	
	public LoginController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.loginRequest = (LoginRequest)enviable;
		this.manejadorUsuario = manejadorUsuario;
	}

	@Override
	public Object manejarMensaje() {
		// TODO(toti): Buscar el usuario a la base de datos, y en caso de que exista,
		// lo seteamos en el manejador de suaurios.
		String mensaje = "Logueo exitoso";
		boolean usuarioValido = loginRequest.getNombre().equals("totiimon")
			&& loginRequest.getPassword().equals("ninaesunagenia");
		if (usuarioValido) {
			manejadorUsuario.setJugador(new Jugador());
		}
		return new LoginResponse(usuarioValido, mensaje);
	}
}
