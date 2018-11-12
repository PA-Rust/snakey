package Server;

import java.io.IOException;
import java.util.ArrayList;

import Commons.Sala;
import Comunicacion.Enviable;

public class ManejadorSala extends Thread {
	private Sala sala;
	private ArrayList<ManejadorUsuario> listeners;
	
	public ManejadorSala(Sala sala, ManejadorUsuario manejadorUsuario) {
		this.sala = sala;
		addListener(manejadorUsuario);
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public void addListener(ManejadorUsuario listener) {
		listeners.add(listener);
	}
	
	public void removeListener(ManejadorUsuario listener) {
		listeners.remove(listener);
	}
	
	public void enNuevoMensaje(Enviable enviable) throws IOException {
		for (ManejadorUsuario listener: listeners) {
			listener.enNuevoMensaje(enviable);
		}
	}
}
