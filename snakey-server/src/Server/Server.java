package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Commons.Jugador;
import Commons.Sala;
import Comunicacion.Enviable;

public class Server {
	int puerto = 3000;
	ArrayList<ManejadorUsuario> manejadoresDeUsuario;
	ArrayList<ManejadorSala> manejadoresDeSala;
	ArrayList<ManejadorJuego> manejadoresDeJuego;
	
	public static void log(String mensaje) {
		System.out.println("SERVER LOG: " + mensaje);
	}
	
	public Server(int puerto) {
		ServerSocket serverSocket;
		try {
			manejadoresDeUsuario = new ArrayList<ManejadorUsuario>();
			serverSocket = new ServerSocket(puerto);
			
			log("Servidor iniciado en puerto " + puerto);
			
			while (true) {
				Socket nuevoUsuario = serverSocket.accept();
				ManejadorUsuario nuevoManejador = new ManejadorUsuario(nuevoUsuario, this);
				manejadoresDeUsuario.add(nuevoManejador);
				log("nuevo cliente unido.");
				nuevoManejador.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean jugadorYaTieneSala(Jugador jugador) {
		boolean encontrado = false;
		for (ManejadorSala manejadorSala: manejadoresDeSala) {
			if (manejadorSala.getSala().getJugadorPropietario() == jugador) {
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public Sala registrarManejadorSala(ManejadorUsuario manejadorUsuario, String nombreSala, int cantidadJugadores) {
		Sala nuevaSala = new Sala(nombreSala, cantidadJugadores, manejadorUsuario.getJugador());
		manejadoresDeSala.add(
			new ManejadorSala(
				nuevaSala,
				manejadorUsuario
			)
		);
		return nuevaSala;
	}
	
	public void eliminatManejadorSala(ManejadorSala manejadorSala) {
		manejadoresDeSala.remove(manejadorSala);
	}
	
	public void deregistrarManejador(ManejadorUsuario manejador) {
		log("Deregistrando manejador");
		manejadoresDeUsuario.remove(manejador);
	}
	
	public static void main(String[] args) {
		new Server(3000);
	}
}
