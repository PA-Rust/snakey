package Commons;

public class Partida {
	private Jugador[] jugadores;
	private int timer;
	private Mapa mapa;
	
	public Partida(Jugador[] jugadores) {
		this.jugadores = jugadores;
		this.timer = 50_000;
		
		this.mapa = new Mapa();
		
		for (Jugador jugador: jugadores) {
			jugador.resetearPuntajeActual();
		}
	}
	
	public Partida(Jugador[] jugadores, int timer) {
		this.jugadores = jugadores;
		
		this.mapa = new Mapa();
		
		for (Jugador jugador: jugadores) {
			jugador.resetearPuntajeActual();
		}
		
		this.timer = timer;
	}
	
	public Mapa getMapa() {
		return mapa;
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
