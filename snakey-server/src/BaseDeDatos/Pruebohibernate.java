package BaseDeDatos;

import Commons.Jugador;

public class Pruebohibernate {

	public static void main(String[] args) {
		JugadorDao daojugador = new JugadorDao();
		Jugador jugador = new Jugador();
		jugador.setNombreDeUsuario("er");
		jugador.setClaveDeUsuario("as");
		daojugador.claveCorrecta(jugador);
		daojugador.cerrarSession();
	}
}
