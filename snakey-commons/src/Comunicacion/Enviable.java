package Comunicacion;

import java.io.Serializable;

public class Enviable implements Serializable {
	private static final long serialVersionUID = 2384505718795431246L;
	
	private TiposMensaje tipoMensaje;
	
	public Enviable(TiposMensaje tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
	
	public TiposMensaje getTipoMensaje() {
		return tipoMensaje;
	}
}
