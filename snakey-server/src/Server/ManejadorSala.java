package Server;

import java.io.IOException;
import java.util.ArrayList;

import Commons.Input;
import Commons.Jugador;
import Commons.Partida;
import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.Notifications.JuegoIniciadoNotification;
import Comunicacion.Notifications.CambioSalaNotification;

public class ManejadorSala extends Thread {
	private Sala sala;
	private ManejadorJuego partidaActual = null;
	private ArrayList<ManejadorUsuario> listeners;
	
	public ManejadorSala(Sala sala, ManejadorUsuario manejadorUsuario) {
		listeners = new ArrayList<ManejadorUsuario>();
		this.sala = sala;
		manejadorUsuario.setSalaActual(sala);
		addListener(manejadorUsuario);
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public void iniciarNuevaPartida() throws IOException {
		partidaActual =
			new ManejadorJuego(new Partida(sala.getJugadores()), listeners);
		for (ManejadorUsuario listener: listeners) {
			listener.enviarMensaje(new JuegoIniciadoNotification(partidaActual.getBucleJuego().getPartida()));
		}
	}
	
	public synchronized void unirNuevoUsuario(ManejadorUsuario manejadorUsuario) {
		sala.agregarJugador(manejadorUsuario.getJugador());
		manejadorUsuario.setSalaActual(sala);
		addListener(manejadorUsuario);
		enviarMensajeListeners(new CambioSalaNotification(sala));
	}
	
	public synchronized void nuevaInput(Jugador jugador, Input input) {
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
		sala.removerJugador(listener.getJugador());
		listener.setSalaActual(null);
		listeners.remove(listener);
		enviarMensajeListeners(new CambioSalaNotification(sala));
	}
	
	public void enviarMensajeListeners(Enviable enviable) {
		for (ManejadorUsuario listener: listeners) {
			listener.enviarMensaje(enviable);
		}
	}
}
