package Server;

public class ManejadorJuego extends Thread {
	private ManejadorUsuario[] jugadores;
	
	public ManejadorJuego(ManejadorUsuario[] jugadores) {
		this.jugadores = jugadores;
	}
	
	public ManejadorUsuario[] getJugadores() {
		return jugadores;
	}
}
