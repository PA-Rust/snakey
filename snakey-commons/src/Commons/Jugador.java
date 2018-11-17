package Commons;

public class Jugador {
	private int id;
	private String nombreDeUsuario;
	private int partidasGanadas;
	private int partidasPerdidas;
	private int puntajeAcumulado;
	
	// TODO(toti): Remover lo de clave, poniendolo para hacer funcionar
	// lo de interfaz grafica
	private String claveDeUsuario;
	
	private transient Avatar avatar;
	private transient int puntajeActual = 0;

	public Jugador() {
		this.id = 0;
		this.nombreDeUsuario = "toti";
		this.avatar = Avatar.VIBORITA_VERDE;
	}
	
	public Jugador(String nombreDeUsuario, int id) {
		this.id = id;
		this.nombreDeUsuario = nombreDeUsuario;
	}
	
	public Avatar getAvatar() {
		return avatar;
	}
	
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	
	public void setClaveUsuario(String clave) {
		this.claveDeUsuario = clave;
	}
	
	public String getClaveUsuario() {
		return claveDeUsuario;
	}

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
}

