package Controller;

import Comunicacion.Enviable;
import Comunicacion.Mensajes.RegisterRequest;
import Comunicacion.Mensajes.RegisterResponse;
import Server.ManejadorUsuario;

public class RegisterController implements Controller {
	private RegisterRequest registerRequest;
	private ManejadorUsuario manejadorUsuario;
	
	public RegisterController(Enviable enviable, ManejadorUsuario manejadorUsuario) {
		this.registerRequest = (RegisterRequest)enviable;
		this.manejadorUsuario = manejadorUsuario;
	}
	
	@Override
	public Object manejarMensaje() {
		String mensaje = "Registro exitoso";
		// TODO: Chequear en la base de datos con los datos (registerRequest)
		// y si los valores ingresados tienen sentido (no estan vacios, etc).
		// TODO(2): Podemos hacer que una vez registrado correctamente logueemos al usuario
		// asignando en el manejadorDeUsuario el Jugador como el jugador recien creado.
		boolean usuarioValido = true; 
		
		if (usuarioValido) {
			// TODO: Crear entrada de usuario nueva.
		}
		
		return new RegisterResponse(usuarioValido, mensaje);
	}
}
