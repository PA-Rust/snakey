package Comunicacion;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;

public class HiloCliente extends Thread {
	private static boolean instanciado = false;
	private static HiloCliente instancia;
	private String estado;
	private Socket server;
	private ObjectOutputStream emisor;
	private ObjectInputStream receptor;
	private ArrayList<Escuchador> escuchadores;
	
	public interface EscuchadorRegister extends Escuchador {
		public void notificarRegisterResponse(Enviable enviable);
	}
	
	public interface EscuchadorLogin extends Escuchador {
		public void notificarLoginResponse(Enviable enviable);
	}
	
	public interface EscuchadorSalas extends Escuchador {
		public void notificarGetSalasResponse(Enviable enviable);
	}
	
	private HiloCliente() {
		this.estado = "no_autenticado";
		HiloCliente.instanciado =  true;
		escuchadores = new ArrayList<Escuchador>();
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
	
	public void agregarEscuchador(Escuchador escuchador) {
		this.escuchadores.add(escuchador);
	}
	
	public void removerEscuchador(Escuchador escuchador) {
		this.escuchadores.remove(escuchador);
	}
	
	@Override
	public void run() {
		try {
			receptor = new ObjectInputStream(server.getInputStream());
			while (server.isConnected()) {
				try {
					Enviable nuevoMensaje = (Enviable) receptor.readObject();
					ManejadorDeRespuestas.getInstancia().manejarMensaje(nuevoMensaje);
				} catch (ClassNotFoundException e) {
					System.out.println("Error de formato de mensaje recibido");
				}
			}
		} catch (EOFException e) {
			System.out.println("conexion terminada");
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
		if (!HiloCliente.instanciado) {
			instancia = new HiloCliente();
		}
		return instancia;
	}
	
	public synchronized void enviarMensaje(Enviable enviable) {
		try {
			emisor.writeObject(enviable);
			emisor.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getEstado() {
		return estado;
	}
}
