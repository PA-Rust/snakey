package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Commons.Sala;
import Comunicacion.Enviable;

public class Server {
	int puerto = 3000;
	ArrayList<ManejadorUsuario> manejadores;
	ArrayList<Sala> salas;
	
	public Server(int puerto) {
		ServerSocket serverSocket;
		try {
			manejadores = new ArrayList<ManejadorUsuario>();
			serverSocket = new ServerSocket(puerto);
			
			System.out.println("Servidor iniciado en puerto " + puerto);
			
			while (true) {
				Socket nuevoUsuario = serverSocket.accept();
				ManejadorUsuario nuevoManejador = new ManejadorUsuario(nuevoUsuario, this);
				manejadores.add(nuevoManejador);
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
	
	public void nuevoMensaje(ManejadorUsuario sender, Enviable mensaje) {
		if (manejadores.isEmpty()) {
			return;
		}
		// System.out.println(mensaje.getNombre() + ": " + mensaje.getMensaje());
		for (ManejadorUsuario manejador: manejadores) {
			if (manejador == sender) {
				// No le vuelve a enviar el mensaje al usuario que envio
				// ese mensaje.
				continue;
			}
			try {
				manejador.enNuevoMensaje(mensaje);
			} catch (IOException e) {
				log("Error al notificar al cliente: " + e);
			}
		}
	}
	
	public void deregistrarManejador(ManejadorUsuario manejador) {
		log("Deregistrando manejador");
		manejadores.remove(manejador);
	}
	
	public static void main(String[] args) {
		new Server(3000);
	}
}
