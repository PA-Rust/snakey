package Commons;

import java.awt.Color;

public class Jugador {
	private int id;
	private String nombreDeUsuario;
	private int partidasGanadas;
	private int partidasPerdidas;
	private int puntajeAcumulado;
	private int puntajeDePartida;
	
	private transient Color color;
	private transient int puntajeActual = 0;

	public Jugador() {
		this.id = 0;
		this.nombreDeUsuario = null;
	}
	
	public Jugador(String nombreDeUsuario, int id) {
		this.id = id;
		this.nombreDeUsuario = nombreDeUsuario;
	}

	/**
	 * TODO (toti): Recordar que estas cosas habra que actualizarlas
	 * en la base de datos.
	 */

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void setNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public int getPartidasGanadas() {
		return partidasGanadas;
	}
	
	public void incrementarPartidasGanas() {
		partidasGanadas++;
	}
	
	public int getPartidasPerdidas() {
		return partidasPerdidas;
	}
	
	public void incrementarPartidasPerdidas() {
		partidasPerdidas++;
	}

	public int getPuntajeAcumulado() {
		return puntajeAcumulado;
	}

	public void incrementarPuntajeAcumulado(int nuevoPuntaje) {
		this.puntajeAcumulado += nuevoPuntaje;
	}
	
	public int getPuntajeActual() {
		return puntajeActual;
	}
	
	public void incrementarPuntajeActual(int puntaje) {
		puntajeActual += puntaje;
	}
	
	public void resetearPuntajeActual() {
		puntajeActual = 0;
	}
<<<<<<< HEAD

	public int getPuntajeDePartida() {
		return puntajeDePartida;
	}

	public void setPuntajeDePartida(int puntajeDePartida) {
		this.puntajeDePartida = puntajeDePartida;
	}
	
=======
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
>>>>>>> comenzando a agregar logica del servidor
}

