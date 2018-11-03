package Viborita;

import java.util.ArrayList;

import Commons.Coordenada;
import Commons.Direccion;
import Commons.Entidad;

public class Viborita extends Entidad {

	private ArrayList<Cuerpo> cuerpo;
	private float velocidad = 1;

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
			.obtenerPosicion();
		nuevaCoordenada.desplazar(desplazamiento);
		
		cuerpo.add(new Cuerpo(nuevaCoordenada));
	}
	
	public void decrecer() {
	}
	
	public void desplazar() {
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
