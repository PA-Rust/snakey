package Commons;

import java.io.Serializable;
import java.util.ArrayList;

public class Sala implements Serializable {
	private static final long serialVersionUID = 3579975091518717473L;
	private String nombreSala;
	private Jugador jugadorPropietario;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Jugador> espectadores;
	private boolean jugando;
	private boolean tieneEspectadores;
	private int cantJugadores;

	public Sala(String nombreSala, int cantJugadores, Jugador propietario) {
		this.nombreSala = nombreSala;
		this.cantJugadores = cantJugadores;
		this.jugadorPropietario = propietario;
		this.tieneEspectadores = false;
		jugadores = new ArrayList<Jugador>(cantJugadores);
		jugadores.add(propietario);
	}
	
	public void agregarJugador(Jugador jugador) {
		jugadores.add(jugador);
	}
	
	public void removerJugador(Jugador jugador) {
		jugadores.remove(jugador);
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
	
	public boolean getJugando() {
		return jugando;
	}
	
	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}
}
