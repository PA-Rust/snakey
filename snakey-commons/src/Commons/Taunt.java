package Commons;

public enum Taunt implements Input {
	REIRSE("HAHAHAHAHAHA"),
	INSULTAR("@#$*!"),
	GG("GG!"),
	LLORAR(":'(");
	
	private String mensaje;
	
	private Taunt(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public String getTipoInput() {
		return "Taunt";
	}
}
