package Server;

import java.io.IOException;
import java.util.ArrayList;

import BaseDeDatos.JugadorDao;
import Commons.Input;
import Commons.Jugador;
import Commons.Partida;
import Commons.Taunt;
import Comunicacion.Notifications.EstadoPartidaNotification;
import Comunicacion.Notifications.JuegoFinalizadoNotification;
import Comunicacion.Notifications.TauntNotification;

public class ManejadorJuego extends Thread {
	private BucleJuego bucleJuego;
	private ManejadorSala manejadorSala;
	private ArrayList<ManejadorUsuario> listeners;
	
	public ManejadorJuego(Partida partida, ManejadorSala manejadorSala) {
		this.bucleJuego = new BucleJuego(partida, this);
		bucleJuego.start();
		this.manejadorSala = manejadorSala;
		this.listeners = manejadorSala.getListeners();
	}
	
	public void eliminarListener(ManejadorUsuario manejador) {
		listeners.remove(manejador);
		bucleJuego.removerJugador(manejador.getJugador());
	}
	
	public void enviarTaunt(Taunt taunt, Jugador jugador) throws IOException {
		for (ManejadorUsuario listener: listeners) {
			listener.enviarMensaje(new TauntNotification(taunt, jugador));
		}
	}
	
	public Jugador getJugadorGanador() {
		Jugador ganador = null;
		for (ManejadorUsuario listener: listeners) {
			Jugador jugador = listener.getJugador();
			if (ganador == null || jugador.getPuntajeActual() > jugador.getPuntajeActual()) {
				jugador = ganador;
			}
		}
		return ganador;
	}
	
	public void enviarPartidaFinalizada() throws IOException {
		JugadorDao dao = new JugadorDao();
		Jugador jugadorGanador = getJugadorGanador();
		for (ManejadorUsuario listener: listeners) {
			if (listeners.size() >= 2) {
				if (listener.getJugador().equals(jugadorGanador)) {
					listener.getJugador().setPartidasGanadas(
						listener.getJugador().getPartidasGanadas() + 1
					);
				}
				listener.getJugador().setPuntajeAcumulado(
					listener.getJugador().getPuntajeActual()
					+ listener.getJugador().getPuntajeAcumulado()
				);
				listener.getJugador().setManzanitasComidasTotales(
					listener.getJugador().getManzanitasComidasTotales() +
					listener.getJugador().getManzanitasComidasActuales()
				);
				dao.actualizarDatos(listener.getJugador());
			}
			listener.enviarMensaje(new JuegoFinalizadoNotification());
		}
		manejadorSala.notificarPartidaTerminada();
	}
	
	public void enviarPartidaListeners() throws IOException {
		for (ManejadorUsuario listener: listeners) {
			listener.enviarMensaje(new EstadoPartidaNotification(this.bucleJuego.getPartida()));
		}
	}
	
	public BucleJuego getBucleJuego() {
		return bucleJuego;
	}
	
	public void nuevaInput(Jugador jugador, Input input) {
		try {
			bucleJuego.nuevaInput(jugador, input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
