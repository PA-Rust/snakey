package Server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Commons.Jugador;
import Commons.Sala;
import Comunicacion.Enviable;
import Comunicacion.Notifications.EstadoPartidaNotification;
import Controller.ControllerFactory;

public class ManejadorUsuario extends Thread {
	private Socket usuario;
	private Server serverSocket;
	private ObjectOutputStream salida;
	private ControllerFactory controllerFactory;
	private Jugador jugador;
	private Sala salaActual;
	
	public ManejadorUsuario(Socket usuario, Server server) {
		this.usuario = usuario;
		this.serverSocket = server;
		this.controllerFactory = new ControllerFactory(this);
		this.jugador = null;
		
		try {
			salida = new ObjectOutputStream(usuario.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			ObjectInputStream entrada =
				new ObjectInputStream(usuario.getInputStream());
			while (usuario.isConnected()) {
				try {
					Enviable nuevoMensaje = (Enviable) entrada.readObject();
					Enviable response = controllerFactory.manejarEnviables(nuevoMensaje).manejarMensaje();
					if (response != null) {
						enviarMensaje(response);
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Error de formato de mensaje recibido");
				}
			}
			entrada.close();
		} catch (EOFException e) {
			System.out.println("connexion terminada");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading info from client");
		} finally {
			System.out.println("Cerrando manejador");
			try {
				serverSocket.deregistrarManejador(this);
				salida.close();
				usuario.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setSalaActual(Sala sala) {
		this.salaActual = sala;
	}
	
	public Server getServerSocket() {
		return serverSocket;
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public synchronized void enviarMensaje(Enviable nuevoMensaje) {
		try {
			salida.writeObject(nuevoMensaje);
			salida.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Sala getSalaActual() {
		return salaActual;
	}
}
