package Comunicacion.Requests;

import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class CrearSalaRequest extends Enviable {
	private String nombreSala;
	private int cantidadJugadores;
	private String clave;
	
	public CrearSalaRequest(String nombreSala, int cantidadJugadores, String clave) {
		super(TiposMensaje.CREATE_SALA_REQUEST);
		this.nombreSala = nombreSala;
		this.cantidadJugadores = cantidadJugadores;
		this.clave = clave;
	}
	
	public String getNombreSala() {
		return nombreSala;
	}
	
	public int getCantidadJugadores() {
		return cantidadJugadores;
	}
	
	public String getClave() {
		return this.clave;
	}
}
