package Viborita;

import java.util.ArrayList;

import Commons.Coordenada;
import Commons.Direccion;
import Commons.Entidad;

public class Viborita extends Entidad {

	private ArrayList<Cuerpo> cuerpo;
	private int velocidad = 1;

	public Viborita(int y) {
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
	
	public int getVelocidad() {
		return velocidad;
	}
	
	public void decrecer() {
	}
	
	public void desplazar() {
		Cuerpo cabezaAnterior = getCabeza();
		Cuerpo cabeza = getCabeza();
		
		Coordenada posicionActual = cabeza.getPosicion();
		switch (cabeza.getDireccion()) {
			case arriba:
				cabeza.setPosicion(new Coordenada(posicionActual.getX(), posicionActual.getY() - velocidad));
			case abajo:
				cabeza.setPosicion(new Coordenada(posicionActual.getX(), posicionActual.getY() + velocidad));
			case izquierda:
				cabeza.setPosicion(new Coordenada(posicionActual.getX() - velocidad, posicionActual.getY()));
			case derecha:
				cabeza.setPosicion(new Coordenada(posicionActual.getX() + velocidad, posicionActual.getY()));
		}
		
		for (int i = 1; i < cuerpo.size(); i++) {
			Cuerpo cuerpoActual = cuerpo.get(i);
			if (i == 1) {
				cuerpoActual.setDireccion(cabezaAnterior.getDireccion());
				cuerpoActual.setPosicion(cabezaAnterior.getPosicion());
				continue;
			}
			cuerpoActual.setDireccion(cuerpo.get(i - 1).getDireccion());
			cuerpoActual.setPosicion(cuerpo.get(i - 1).getPosicion());
		}
		
	}
	
	/**
	 * Se devuelve la coordenada que va a seguir en base al movimiento
	 * que lleva la viborita.
	 * @return Coordenada proximaCoordenada
	 */
	public Coordenada getProximaUbicacion() {
		Coordenada proximaCoordenada;
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
			default:
				proximaCoordenada = new Coordenada(0, 0);
		}
		
		return proximaCoordenada;
	}
	
	public void actualizar() {
	}
	
	public void dibujar() {
	}
	
	public void escucharTeclas() {
	}
	
	public void cambiarDireccion(Direccion direccion) {
	}
	
	@Override
	public void enColision(Entidad entidad) {
	}
}
