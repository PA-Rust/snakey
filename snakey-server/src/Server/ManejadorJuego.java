package Server;

import java.io.IOException;
import java.util.ArrayList;

import Commons.Input;
import Commons.Jugador;
import Commons.Partida;
import Comunicacion.Notifications.EstadoPartidaNotification;
import Comunicacion.Notifications.JuegoFinalizadoNotification;

public class ManejadorJuego extends Thread {
	private BucleJuego bucleJuego;
	private ArrayList<ManejadorUsuario> listeners;
	
	public ManejadorJuego(Partida partida, ArrayList<ManejadorUsuario> listeners) {
		this.bucleJuego = new BucleJuego(partida, this);
		this.listeners = listeners;
	}
	
	public void enviarPartidaFinalizada() throws IOException {
		for (ManejadorUsuario listener: listeners) {
			listener.enviarMensaje(new JuegoFinalizadoNotification());
		}
	}
	
	public void enviarPartidaListeners(Partida partida) throws IOException {
		for (ManejadorUsuario listener: listeners) {
			listener.enviarMensaje(new EstadoPartidaNotification(partida));
		}
	}
	
	public BucleJuego getBucleJuego() {
		return bucleJuego;
	}
	
	public void nuevaInput(Jugador jugador, Input input) {
		bucleJuego.nuevaInput(jugador, input);
	}
}
