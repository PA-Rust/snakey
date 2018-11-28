package Server;

import java.io.IOException;
import java.util.ArrayList;

import Commons.Sala;
import Comunicacion.Enviable;
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
	
	public synchronized void unirNuevoUsuario(ManejadorUsuario manejadorUsuario) {
		try {
			enNuevoMensaje(new NuevoUsuarioNotification(manejadorUsuario.getJugador()));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// TODO(toti): Tal vez podriamos mover el addListener
			// al try y en el catch volver a intentar unirlo.
			manejadorUsuario.setSalaActual(sala);
			addListener(manejadorUsuario);
		}
	}
	
	public ManejadorJuego getPartidaActual() {
		return partidaActual;
	}
	
	public void addListener(ManejadorUsuario listener) {
		listeners.add(listener);
	}
	
	public void removeListener(ManejadorUsuario listener) {
		listener.setSalaActual(null);
		listeners.remove(listener);
	}
	
	public void enNuevoMensaje(Enviable enviable) throws IOException {
		for (ManejadorUsuario listener: listeners) {
			listener.enNuevoMensaje(enviable);
		}
	}
}
