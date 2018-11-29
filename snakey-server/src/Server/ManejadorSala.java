package Server;

import java.io.IOException;
import java.util.ArrayList;

import Commons.Input;
import Commons.Jugador;
import Commons.Partida;
import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.Notifications.JuegoIniciadoNotification;
import Comunicacion.Notifications.NuevoUsuarioNotification;

public class ManejadorSala extends Thread {
	private Sala sala;
	private ManejadorJuego partidaActual = null;
	private ArrayList<ManejadorUsuario> listeners;
	
	public ManejadorSala(Sala sala, ManejadorUsuario manejadorUsuario) {
		this.sala = sala;
		addListener(manejadorUsuario);
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public void iniciarNuevaPartida() throws IOException {
		partidaActual =
			new ManejadorJuego(new Partida((Jugador[]) sala.getJugadores().toArray()), listeners);
		for (ManejadorUsuario listener: listeners) {
			listener.enviarMensaje(new JuegoIniciadoNotification(partidaActual.getBucleJuego().getPartida()));
		}
	}
	
	public synchronized void unirNuevoUsuario(ManejadorUsuario manejadorUsuario) {
		sala.agregarJugador(manejadorUsuario.getJugador());
		manejadorUsuario.setSalaActual(sala);
		addListener(manejadorUsuario);
		enNuevoMensaje(new NuevoUsuarioNotification(manejadorUsuario.getJugador()));
	}
	
	public void nuevaInput(Jugador jugador, Input input) {
		if (partidaActual != null) {
			partidaActual.nuevaInput(jugador, input);
		}
	}
	
	public ManejadorJuego getPartidaActual() {
		return partidaActual;
	}
	
	public void addListener(ManejadorUsuario listener) {
		listeners.add(listener);
	}
	
	public void removeListener(ManejadorUsuario listener) {
		sala.agregarJugador(listener.getJugador());
		listener.setSalaActual(null);
		listeners.remove(listener);
	}
	
	public void enNuevoMensaje(Enviable enviable) {
		for (ManejadorUsuario listener: listeners) {
			try {
				listener.enviarMensaje(enviable);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
