package Comunicacion;

public class EstadosAplicacion {
	private static EstadosAplicacion instancia = null;
	private String serverIP = "127.0.0.1";
	private int serverPuerto = 3000;
	
	private EstadosAplicacion() { }
	
	public static EstadosAplicacion getInstancia() {
		if (instancia == null) {
			instancia = new EstadosAplicacion();
		}
		return instancia;
	}
	
	public String getServerIP() {
		return serverIP;
	}
	
	public int getServerPuerto() {
		return serverPuerto;
	}
	
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	
	public void setServerPuerto(int serverPuerto) {
		this.serverPuerto = serverPuerto;
	}
}
