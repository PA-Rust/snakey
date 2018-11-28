package BaseDeDatos;

import Commons.Jugador;

public class Pruebohibernate {

	public static void main(String[] args) {
		JugadorDao daojugador = new JugadorDao();
		Jugador jugador = new Jugador();
		daojugador.actualizarDatos(jugador);
		daojugador.cerrarSession();
	}
}
