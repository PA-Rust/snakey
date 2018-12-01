package Comunicacion.Notifications;

import Commons.Jugador;
import Commons.Taunt;
import Comunicacion.Enviable;
import Comunicacion.TiposMensaje;

public class TauntNotification extends Enviable {
	private static final long serialVersionUID = 8952746992178160856L;
	private Taunt taunt;
	private Jugador jugador;
	
	public TauntNotification(Taunt taunt, Jugador jugador) {
		super(TiposMensaje.TAUNT);
		this.taunt = taunt;
		this.jugador = jugador;
	}
	
	public Taunt getTaunt() {
		return taunt;
	}
	
	public Jugador getJugador() {
		return jugador;
	}
}
