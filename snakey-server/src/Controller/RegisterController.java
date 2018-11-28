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
		System.out.println("Nuevo mensaje de register request???" + enviable);
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
		// TODO: Chequear en la base de datos con los datos (registerRequest)
		// y si los valores ingresados tienen sentido (no estan vacios, etc).
		// TODO(2): Podemos hacer que una vez registrado correctamente logueemos al usuario
		// asignando en el manejadorDeUsuario el Jugador como el jugador recien creado.
//		boolean usuarioValido = true; 
//		
//		if (usuarioValido) {
//			// TODO: Crear entrada de usuario nueva.
//		}
		
//		return new RegisterResponse(usuarioValido, mensaje);
	}
}
