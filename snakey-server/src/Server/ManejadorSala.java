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
	private Server server;
	private ManejadorJuego partidaActual = null;
	private ArrayList<ManejadorUsuario> listeners;
	
	public ManejadorSala(Server server, Sala sala, ManejadorUsuario manejadorUsuario) {
		this.server = server;
		listeners = new ArrayList<ManejadorUsuario>();
		this.sala = sala;
		manejadorUsuario.setSalaActual(sala);
		addListener(manejadorUsuario);
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public void notificarPartidaTerminada() {
		sala.setJugando(false);
		partidaActual = null;
	}
	
	public void iniciarNuevaPartida() throws IOException {
		sala.setJugando(true);
		partidaActual =
			new ManejadorJuego(new Partida(sala.getJugadores()), this);
		for (ManejadorUsuario listener: listeners) {
			listener.enviarMensaje(new JuegoIniciadoNotification(partidaActual.getBucleJuego().getPartida()));
		}
	}
	
	public synchronized void unirNuevoUsuario(ManejadorUsuario manejadorUsuario) {
		sala.agregarJugador(manejadorUsuario.getJugador());
		manejadorUsuario.setSalaActual(sala);
		enviarMensajeListeners(new CambioSalaNotification(sala));
		addListener(manejadorUsuario);
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
		
		if (partidaActual != null) {
			partidaActual.eliminarListener(listener);
		}
		
		enviarMensajeListeners(new CambioSalaNotification(sala));
		
		if (sala.getJugadorPropietario().equals(listener.getJugador())) {
			listeners.removeAll(listeners);
			server.eliminarManejadorSala(this);
		}
	}
	
	public void enviarMensajeListeners(Enviable enviable) {
		for (ManejadorUsuario listener: listeners) {
			listener.enviarMensaje(enviable);
		}
	}
	
	public ArrayList<ManejadorUsuario> getListeners() {
		return listeners;
	}
}
