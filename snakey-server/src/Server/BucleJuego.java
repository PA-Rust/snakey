package Server;

import java.io.IOException;

import Commons.Direccion;
import Commons.Input;
import Commons.Jugador;
import Commons.Partida;
import Viborita.Viborita;

public class BucleJuego extends Thread {
	private Partida partida;
	private ManejadorJuego manejadorJuego;
	
	private final int TICKS_PER_SECOND = 25;
	private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
	private final int MAX_FRAMESKIP = 5;
	
	public BucleJuego(Partida partida, ManejadorJuego manejadorJuego) {
		this.partida = partida;
		this.manejadorJuego = manejadorJuego;
	}

	@Override
	public void run() {
		double nextGameTick = System.currentTimeMillis();
	    int loops;

	    while (partida.getTimer() > 0 || partida.getMapa().getViboritas().size() != 0) {
	        loops = 0;
	        while (System.currentTimeMillis() > nextGameTick
	                && loops < MAX_FRAMESKIP) {
	        	partida.getMapa().actualizar();
	        	partida.decrementarTimer(1);
	        	nextGameTick += SKIP_TICKS;

		        try {
					manejadorJuego.enviarPartidaListeners();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            loops++;
	        }
	    }
	    
	    try {
			manejadorJuego.enviarPartidaFinalizada();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Partida getPartida() {
		return partida;
	}
	
	public void removerJugador(Jugador jugador) {
		Viborita viboritaJugador = partida.getViboritaJugador(jugador);
		if (viboritaJugador != null) {
			partida.getMapa().getViboritas().remove(viboritaJugador);
		}
	}
	
	public synchronized void nuevaInput(Jugador jugador, Input input) {
		if (input.getTipoInput() == "Direccion") {
			Direccion nuevaDireccion = (Direccion) input;
			Viborita viborita = partida.getViboritaJugador(jugador);
			if (viborita != null) {
				viborita.cambiarDireccion(nuevaDireccion);
			}
		} else {
			// TODO (toti): Notificar a todos los listeners de un nuevo Taunt
			// lanzado en la partida por el `jugador`.
		}
	}
}
