package Commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class Sala implements Serializable {
	private static final long serialVersionUID = 3579975091518717473L;
	private String nombreSala;
	private Jugador jugadorPropietario;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Jugador> espectadores;
	private boolean jugando;
	private boolean tieneEspectadores;
	private int cantJugadores;
	private Stack<Avatar> avatares;
	private transient String claveSala;
	private boolean tieneClave;
	
	public Sala(String nombreSala, int cantJugadores, Jugador propietario,String clave) {
		avatares = new Stack<Avatar>();
		inicializarStackAvatares();
		this.nombreSala = nombreSala;
		this.cantJugadores = cantJugadores;
		this.jugadorPropietario = propietario;
		this.tieneEspectadores = false;
		this.claveSala = clave;
		if(clave != null)
			this.tieneClave = true;
		jugadores = new ArrayList<Jugador>(cantJugadores);
		propietario.setAvatar(avatares.pop());
		jugadores.add(propietario);
	}
	
	public void inicializarStackAvatares() {
		avatares.push(Avatar.VIBORITA_AMARILLA);
		avatares.push(Avatar.VIBORITA_NARANJA);
		avatares.push(Avatar.VIBORITA_AZUL);
		avatares.push(Avatar.VIBORITA_VERDE);
	}
	
	public void agregarJugador(Jugador jugador) {
		jugador.setAvatar(avatares.pop());
		jugadores.add(jugador);
	}
	
	public void removerJugador(Jugador jugador) {
		jugadores.remove(jugador);
		avatares.push(jugador.getAvatar());
	}
	
	public boolean getTieneClave() {
		return this.tieneClave;
	}
	
	public String getClaveSala() {
		return claveSala;
	}

	public void setClaveSala(String claveSala) {
		this.claveSala = claveSala;
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
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Sala)) {
			return false;
		}
		
		Sala sala = (Sala) obj;
		return jugadorPropietario.equals(sala.getJugadorPropietario());
	}
}
