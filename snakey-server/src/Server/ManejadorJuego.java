package Server;

import java.io.IOException;
import java.util.ArrayList;

import BaseDeDatos.JugadorDao;
import Commons.Input;
import Commons.Jugador;
import Commons.Partida;
import Comunicacion.Notifications.EstadoPartidaNotification;
import Comunicacion.Notifications.JuegoFinalizadoNotification;

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
	
	public void enviarPartidaFinalizada() throws IOException {
		JugadorDao dao = new JugadorDao();
		for (ManejadorUsuario listener: listeners) {
			listener.getJugador().setPuntajeAcumulado(
				listener.getJugador().getPuntajeActual()
				+ listener.getJugador().getPuntajeAcumulado()
			);
			dao.actualizarDatos(listener.getJugador());
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
		bucleJuego.nuevaInput(jugador, input);
	}
}
