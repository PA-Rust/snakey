package Commons;

import java.util.ArrayList;

public class Sala {
	private String nombreSala;
	private ArrayList<Jugador> jugadores;
	private boolean tieneEspectadores;
	private ArrayList<Jugador> espectadores;
	private Jugador jugadorPropietario;
	private int cantJugadores;

	public Sala(String nombreSala, int cantJugadores, Jugador propietario) {
		this.nombreSala = nombreSala;
		this.cantJugadores = cantJugadores;
		this.jugadorPropietario = propietario;
		this.tieneEspectadores = false;
		jugadores = new ArrayList<Jugador>(cantJugadores);
		jugadores.add(propietario);
	}
	
	public String getNombreSala() {
		return nombreSala;
	}

	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	
	public int getCantJugadoresActual() {
		return jugadores.size();
	}
	
	public boolean getSalaLlena() {
		return jugadores.size() == cantJugadores;
	}

	public int getCantJugadores() {
		return cantJugadores;
	}

	public void setCantJugadores(int cantJugadores) {
		this.cantJugadores = cantJugadores;
	}

	public Jugador getJugadorPropietario() {
		return jugadorPropietario;
	}

	public void setJugadorPropietario(Jugador jugadorPropietario) {
		this.jugadorPropietario = jugadorPropietario;
	}
	
	public boolean getTieneEspectadores() {
		return tieneEspectadores;
	}
	
	public ArrayList<Jugador> getEspectadores() {
		return espectadores;
	}
}
