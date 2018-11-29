package Commons;

import java.io.Serializable;
import Viborita.Viborita;

public class Partida implements Serializable {
	private static final long serialVersionUID = -7869712406560321495L;
	private Jugador[] jugadores;
	private int timer;
	private Mapa mapa;
	boolean running;
	
	public Partida(Jugador[] jugadores) {
		this.jugadores = jugadores;
		this.timer = 50_000;
		this.mapa = new Mapa(jugadores);
		inicializarJugadores(jugadores);
	}
	
	public void inicializarJugadores(Jugador[] jugadores) {
		for (int i = 0; i < jugadores.length; ++i) {
			jugadores[i].resetearPuntajeActual();
		}
	}
	
	public Mapa getMapa() {
		return mapa;
	}
	
	public Viborita getViboritaJugador(Jugador jugador) {
		Viborita viborita = null;
		for (Viborita viboritaActual: getMapa().getViboritas()) {
			if (viborita.getJugador() == jugador) {
				viborita = viboritaActual;
			}
		}
		return viborita;
	}
	
	public Jugador[] getJugadores() {
		return jugadores;
	}
	
	public int getTimer() {
		return timer;
	}
	
	public void decrementarTimer(int cantidad) {
		timer -= cantidad;
	}
	
	public void incrementarPuntajeJugador(int puntaje, Jugador jugador) {
		jugador.incrementarPuntajeActual(puntaje);
	}
}
