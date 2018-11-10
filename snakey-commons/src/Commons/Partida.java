package Commons;

import java.awt.Color;

public class Partida implements Runnable {
	private Jugador[] jugadores;
	private int timer;
	private Mapa mapa;
	private Color[] colores = new Color[] {
			Color.blue, Color.yellow, Color.green, Color.pink,
	};
	boolean running;
	
	public Partida(Jugador[] jugadores) {
		this.jugadores = jugadores;
		this.timer = 50_000;
		this.mapa = new Mapa();
		inicializarJugadores(jugadores);
	}
	
	public Partida(Jugador[] jugadores, int timer) {
		this.jugadores = jugadores;
		this.mapa = new Mapa();
		this.timer = timer;
		inicializarJugadores(jugadores);
	}
	
	public void inicializarJugadores(Jugador[] jugadores) {
		for (int i = 0; i < jugadores.length; ++i) {
			jugadores[i].resetearPuntajeActual();
			jugadores[i].setColor(colores[i]);
		}
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
	
	public void tick() {
		
	}

	@Override
	public void run() {
		double ns = 1000000000.0 / 60.0;
	    double delta = 0;

	    long lastTime = System.nanoTime();
	    long timer = System.currentTimeMillis();

	    while (running) {
	        long now = System.nanoTime();
	        delta += (now - lastTime) / ns;
	        lastTime = now;

	        while (delta >= 1) {
	            tick();
	            delta--;
	        }
	    }
	}
}
