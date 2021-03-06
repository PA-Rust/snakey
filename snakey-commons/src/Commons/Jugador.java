package Commons;

import java.io.Serializable;

public class Jugador implements Serializable {
	private static final long serialVersionUID = 5586482952939968026L;
	transient private String claveDeUsuario;
	private String nombreDeUsuario;
	private int partidasGanadas;
	private int partidasPerdidas;
	private int puntajeAcumulado;
	private Avatar avatar;
	private int puntajeActual = 0;
	private int manzanitasComidasActuales;
	private int manzanitasComidasTotales;

	public Jugador() {
		this.nombreDeUsuario = "toti";
		this.avatar = Avatar.VIBORITA_VERDE;
	}
	
	public Jugador(String nombreDeUsuario,String claveDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
		this.claveDeUsuario = claveDeUsuario;
	}
	
	public int getManzanitasComidasTotales() {
		return manzanitasComidasTotales;
	}
	
	public void setManzanitasComidasTotales(int comidasTotales) {
		this.manzanitasComidasTotales = comidasTotales;
	}

	public int getManzanitasComidasActuales() {
		return manzanitasComidasActuales;
	}
	
	public void setManzanitasComidasActuales(int comidasActuales) {
		this.manzanitasComidasActuales = comidasActuales;
	}
	
	public Avatar getAvatar() {
		return avatar;
	}
	
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	

	public String toString() {
		return nombreDeUsuario + " " + partidasGanadas + " " + partidasPerdidas + " " + puntajeAcumulado;
	}
	
	public void incrementarPartidasGanas() {
		partidasGanadas++;
	}
	
	public void incrementarPartidasPerdidas() {
		partidasPerdidas++;
	}

	public void incrementarPuntajeAcumulado(int nuevoPuntaje) {
		this.puntajeAcumulado += nuevoPuntaje;
	}
	
	public void incrementarPuntajeActual(int puntaje) {
		puntajeActual += puntaje;
	}
	
	public void resetearPuntajeActual() {
		puntajeActual = 0;
	}

	public String getClaveDeUsuario() {
		return claveDeUsuario;
	}

	public void setClaveDeUsuario(String claveDeUsuario) {
		this.claveDeUsuario = claveDeUsuario;
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

	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}

	public int getPartidasPerdidas() {
		return partidasPerdidas;
	}

	public void setPartidasPerdidas(int partidasPerdidas) {
		this.partidasPerdidas = partidasPerdidas;
	}

	public int getPuntajeAcumulado() {
		return puntajeAcumulado;
	}

	public void setPuntajeAcumulado(int puntajeAcumulado) {
		this.puntajeAcumulado = puntajeAcumulado;
	}
	
	public int getPuntajeActual() {
		return puntajeActual;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Jugador)) {
			return false;
		}
		
		Jugador jugador = (Jugador) object;
		return nombreDeUsuario.equals(jugador.getNombreDeUsuario());
	}

}

