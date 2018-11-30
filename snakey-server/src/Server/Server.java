package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import BaseDeDatos.HibernateSingleton;
import Commons.Jugador;
import Commons.Sala;

public class Server {
	ArrayList<ManejadorUsuario> manejadoresDeUsuario;
	ArrayList<ManejadorSala> manejadoresDeSala;
	
	public static void log(String mensaje) {
		System.out.println("SERVER LOG: " + mensaje);
	}
	
	public Server(int puerto) {
		// Caliento la base de datos
		HibernateSingleton.getSessionFactory().openSession();
		
		manejadoresDeSala = new ArrayList<ManejadorSala>();
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
	
	public synchronized ArrayList<Sala> getSalas() {
		ArrayList<Sala> salas = new ArrayList<Sala>();
		for (ManejadorSala manejador: manejadoresDeSala) {
			salas.add(manejador.getSala());
		}
		
		return salas;
	}
	
	public synchronized ManejadorSala getManejadorSala(Sala sala) {
		for (ManejadorSala manejador: manejadoresDeSala) {
			if (manejador.getSala().equals(sala)) {
				return manejador;
			}
		}
		return null;
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
	
	public void eliminarManejadorSala(ManejadorSala manejadorSala) {
		manejadoresDeSala.remove(manejadorSala);
	}
	
	public void deregistrarManejador(ManejadorUsuario manejador) {
		log("Deregistrando manejador");
		
		// TODO(toti): Acordarse de borrar como listener de sala y
		// juego en caso de estar participando de uno / varios de ellos.
		if (manejador.getSalaActual() != null) {
			getManejadorSala(manejador.getSalaActual()).removeListener(manejador);
		}
		manejadoresDeUsuario.remove(manejador);
	}
	
	public static void main(String[] args) {
		new Server(3000);
	}
}
