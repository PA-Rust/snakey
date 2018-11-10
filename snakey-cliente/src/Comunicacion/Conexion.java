package Comunicacion;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Conexion extends Thread {
	boolean connected = true;
	ObjectInputStream entrada;
	Socket server;
	ObjectOutputStream mensaje;
	
	public Conexion(String ip, int puerto) {
		try {
			server = new Socket(ip, puerto);
			mensaje = new ObjectOutputStream(server.getOutputStream());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void run() {
		try {
			System.out.println("running thread");
			entrada = new ObjectInputStream(server.getInputStream());
			while (connected) {
				try {
					Enviable nuevoMensaje = (Enviable)entrada.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			entrada.close();
		} catch (EOFException e) {
			System.out.println("connexion terminada");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading info from server");
		} finally {
			System.out.println("Cerrando receptor");
			try {
				mensaje.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Socket server = new Socket("localhost", 3000);
		
	}
}
