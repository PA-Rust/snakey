package Commons;

import java.util.ArrayList;

public class Sala {

	private String nombreSala;
	private ArrayList<Jugador> jugadores;
	private int cantJugadores;
	private Jugador jugadorPropietario;

	//en el contructor no pongo el arraylist ya que se van a ir agregando a medida que ingresen los jugadores
	public Sala(String nombreSala, int cantJugadores, Jugador propietario) {
		this.nombreSala = nombreSala;
		this.cantJugadores = cantJugadores;
		this.jugadorPropietario = propietario;
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

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
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

}
