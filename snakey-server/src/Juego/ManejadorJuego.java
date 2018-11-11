package Juego;

import Server.ManejadorUsuario;

public class ManejadorJuego {
	private ManejadorUsuario[] jugadores;
	
	public ManejadorJuego(ManejadorUsuario[] jugadores) {
		this.jugadores = jugadores;
	}
	
	public ManejadorUsuario[] getJugadores() {
		return jugadores;
	}
}
