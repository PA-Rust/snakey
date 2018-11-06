package Commons;

import java.util.ArrayList;

public class Sala {

	private String nombreSala;
	private ArrayList<Jugador> jugadores;
	private int cantJugadores;

	//en el contructor no pongo el arraylist ya que se van a ir agregando a medida que ingresen los jugadores
	public Sala(String nombreSala, int cantJugadores) {

		this.nombreSala = nombreSala;
		this.cantJugadores = cantJugadores;
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

}
