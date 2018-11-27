package BaseDeDatos;

import Commons.Jugador;

public class Pruebohibernate {

	public static void main(String[] args) {
		///solamente muestro y pruebo el uso correcto,adrian el que lo lea
		JugadorDao daojugador = new JugadorDao();
		Jugador jugador = new Jugador();
//		daojugador.mostrarJugadores();
		jugador.setNombreDeUsuario("julian");
		jugador.setClaveDeUsuario ("1235123123");
		daojugador.insertarEnBdd(jugador);
//		daojugador.existenciaDeJugador(jugador); // ahora lo veo
		daojugador.cerrarSession();
	}
}
