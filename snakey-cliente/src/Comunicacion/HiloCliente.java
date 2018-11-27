package Comunicacion;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class HiloCliente extends Thread {
	private static boolean instanciado = false;
	private static HiloCliente instancia;
	private String estado;
	private Socket server;
	private ObjectOutputStream emisor;
	private ObjectInputStream receptor;
	
	
	private HiloCliente() {
		this.estado = "no_autenticado";
		this.instanciado =  true;
		try {
			server = new Socket("localhost", 3000);
			emisor = new ObjectOutputStream(server.getOutputStream());
			this.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			receptor = new ObjectInputStream(server.getInputStream());
			while (server.isConnected()) {
				
			}
		} catch (EOFException e) {
			System.out.println("connexion terminada");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading info from server");
		} finally {
			try {
				emisor.close();
				receptor.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static HiloCliente getInstance() {
		if (!instanciado) {
			instancia = new HiloCliente();
		}
		return instancia;
	}
	
	public void enMensaje(Enviable enviable) {
		try {
			emisor.writeObject(enviable);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getEstado() {
		return estado;
	}
}
