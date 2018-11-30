package Commons;

import java.io.Serializable;
import java.util.ArrayList;

import Viborita.Viborita;

public class Partida implements Serializable {
	private static final long serialVersionUID = -7869712406560321495L;
	private ArrayList<Jugador> jugadores;
	private int timer;
	private Mapa mapa;
	boolean running;
	
	public Partida(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
		this.timer = 3600;
		this.mapa = new Mapa(jugadores);
		inicializarJugadores(jugadores);
	}
	
	public Partida(Partida partida) {
		this.jugadores = partida.jugadores;
		this.timer = partida.timer;
		this.mapa = partida.mapa;
		this.running = partida.running;
	}

	public void inicializarJugadores(ArrayList<Jugador> jugadores) {
		for (Jugador jugador: jugadores) {
			jugador.resetearPuntajeActual();
		}
	}
	
	public Mapa getMapa() {
		return mapa;
	}
	
	public Viborita getViboritaJugador(Jugador jugador) {
		Viborita viborita = null;
		for (Viborita viboritaActual: getMapa().getViboritas()) {
			if (viboritaActual.getJugador() == jugador) {
				viborita = viboritaActual;
			}
		}
		return viborita;
	}
	
	public ArrayList<Jugador> getJugadores() {
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
