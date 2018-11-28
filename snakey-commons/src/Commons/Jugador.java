package Commons;
	
public class Jugador {
	private String nombreDeUsuario;
	transient private String claveDeUsuario;
	private int partidasGanadas;
	private int partidasPerdidas;
	private int puntajeAcumulado;
	
	private transient Avatar avatar;
	private transient int puntajeActual = 0;

	public Jugador() {
		this.nombreDeUsuario = "toti";
		this.avatar = Avatar.VIBORITA_VERDE;
	}
	
	public Jugador(String nombreDeUsuario,String claveDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
		this.claveDeUsuario = claveDeUsuario;
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


}

