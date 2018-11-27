package Comunicacion.Mensajes;

import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class CrearSalaRequest extends Enviable {
	private String nombreSala;
	private int cantidadJugadores;
	
	public CrearSalaRequest(String nombreSala, int cantidadJugadores) {
		super(TiposMensaje.CREATE_SALA_REQUEST);
		this.nombreSala = nombreSala;
		this.cantidadJugadores = cantidadJugadores;
	}
	
	public String getNombreSala() {
		return nombreSala;
	}
	
	public int getCantidadJugadores() {
		return cantidadJugadores;
	}
}
