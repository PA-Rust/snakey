package Viborita;

import java.awt.Color;
import java.util.ArrayList;

import Commons.Avatar;
import Commons.Coordenada;
import Commons.Direccion;
import Commons.Entidad;
import Commons.Jugador;

public class Viborita extends Entidad {
	private Jugador jugador;
	private ArrayList<Cuerpo> cuerpo;
	private int velocidad = 1;
	
	public Viborita(int y, Jugador jugador) {
		this.jugador = jugador;
		cuerpo = new ArrayList<Cuerpo>();
		for (int x = 4; x > 0; x--) {
			cuerpo.add(new Cuerpo(new Coordenada(x, y)));
		}
	}

	public Cuerpo getCabeza() {
		return cuerpo.get(0);
	}
	
	public ArrayList<Cuerpo> getCuerpo() {
		return cuerpo;
	}
	
	public void crecer() {
		/**
		 * Podriamos hacer que el nuevo 'cuerpo'
		 * aparezca en la misma posicion que la cola de la serpiente
		 * y que a la hora de movernos cuando detectamos que la
		 * posicion del cuerpo en el que estamos
		 * es la misma que la del cuerpo anterior antes de moverse
		 * se quede en el mismo lugar por ese 'frame'.
		 */
		Cuerpo colaDeLaSerpiente = cuerpo.get(cuerpo.size() - 1);
		Direccion direccion = colaDeLaSerpiente.getDireccion();
		Coordenada desplazamiento;
		
		switch (direccion) {
			case arriba:
				desplazamiento = new Coordenada(0, 1);
				break;
			case abajo:
				desplazamiento = new Coordenada(0, -1);
				break;
			case izquierda:
				desplazamiento = new Coordenada(1, 0);
			case derecha:
				desplazamiento = new Coordenada(-1, 0);
			default:
				desplazamiento = new Coordenada(0, 0);
		}
		
		Coordenada nuevaCoordenada = colaDeLaSerpiente
			.getPosicion();
		nuevaCoordenada.desplazar(desplazamiento);
		
		cuerpo.add(new Cuerpo(nuevaCoordenada));
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public int getVelocidad() {
		return velocidad;
	}
	
	public void desplazar() {
		for (int i = cuerpo.size() - 1; i >= 0; i--) {
			Cuerpo cuerpoActual = cuerpo.get(i);
			if (i == 0) {
				cuerpoActual.setPosicion(getProximaUbicacion());
			} else {
				cuerpoActual.setDireccion(cuerpo.get(i - 1).getDireccion());
				cuerpoActual.setPosicion(cuerpo.get(i - 1).getPosicion());
			}
		}
	}
	
	public Coordenada getProximaUbicacion() {
		Coordenada proximaCoordenada = new Coordenada();
		Coordenada posicionActual = getCabeza().getPosicion();

		switch (getCabeza().getDireccion()) {
			case arriba:
				proximaCoordenada = new Coordenada(posicionActual.getX(), posicionActual.getY() - velocidad);
			case abajo:
				proximaCoordenada = new Coordenada(posicionActual.getX(), posicionActual.getY() + velocidad);
			case izquierda:
				proximaCoordenada = new Coordenada(posicionActual.getX() - velocidad, posicionActual.getY());
			case derecha:
				proximaCoordenada = new Coordenada(posicionActual.getX() + velocidad, posicionActual.getY());
		}
		
		return proximaCoordenada;
	}
	
	@Override
	public void actualizar() {
		desplazar();
	}
	
	public Avatar getAvatar() {
		return jugador.getAvatar();
	}
	
	public void cambiarDireccion(Direccion direccion) {
		getCabeza().setDireccion(direccion);
	}
	
	@Override
	public void enColision(Entidad entidad) {
	}
}
