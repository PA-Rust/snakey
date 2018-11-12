package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Commons.Sala;
import Comunicacion.Enviable;

public class Server {
	int puerto = 3000;
	ArrayList<ManejadorUsuario> manejadoresDeUsuario;
	ArrayList<ManejadorSala> manejadoresDeSala;
	ArrayList<ManejadorJuego> manejadoresDeJuego;
	ArrayList<Sala> salas;
	
	public Server(int puerto) {
		ServerSocket serverSocket;
		try {
			manejadoresDeUsuario = new ArrayList<ManejadorUsuario>();
			serverSocket = new ServerSocket(puerto);
			
			System.out.println("Servidor iniciado en puerto " + puerto);
			
			while (true) {
				Socket nuevoUsuario = serverSocket.accept();
				ManejadorUsuario nuevoManejador = new ManejadorUsuario(nuevoUsuario, this);
				manejadoresDeUsuario.add(nuevoManejador);
				log("SERVER LOG: nuevo cliente unido.");
				nuevoManejador.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void log(String mensaje) {
		System.out.println("SERVER LOG: " + mensaje);
	}
	
	public void deregistrarManejador(ManejadorUsuario manejador) {
		log("Deregistrando manejador");
		manejadoresDeUsuario.remove(manejador);
	}
	
	public static void main(String[] args) {
		new Server(3000);
	}
}
