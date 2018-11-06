package Commons;

import java.util.ArrayList;

import Items.Item;
import Viborita.Cuerpo;
import Viborita.Viborita;

public class Mapa {
	private ArrayList<Viborita> viboritas;
	private Item item;
	private int ancho;
	private int alto;
	private Entidad[][] grilla;

	public Mapa() {
		this.ancho = 30;
		this.alto = 30;
	}
	
	public Mapa(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	// TODO(toti): Tal vez podriamos recibir un deltaTime para ayudar con las colisiones.
	public void actualizar() {
		// Cuando el reloj del item vuelve a 0 por ser comido
		// o por terminar normalmente este, se debera generar un nuevo item
		// para poner en el mapa.
		if (item.getReloj() == 0) {
			// spawnearNuevoItem();
		}

		limpiarGrilla();
		reubicarViboritas();
		
		chequearColisiones();
	}
	
	public ArrayList<Viborita> getViboritas() {
		return viboritas;
	}
	
	public void removerViborita(Viborita viborita) {
		viboritas.remove(viborita);
	}
	
	public void dibujar() {
	}
	
	public void reubicarViboritas() {
		for (Viborita viborita: viboritas) {
			for (Cuerpo parte: viborita.getCuerpo()) {
				Coordenada coordenadaParte = parte.obtenerPosicion();
				grilla[coordenadaParte.getX()][coordenadaParte.getY()] = parte;
			}
		}
	}
	
	public void limpiarGrilla() {
		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < alto; y++) {
				grilla[x][y] = null;
			}
		}
		Coordenada coordenadaItem = item.obtenerPosicion();
		grilla[coordenadaItem.getX()][coordenadaItem.getY()] = item;
	}
	
	public void chequearColisiones() {
		for (Viborita viborita: viboritas) {
			Coordenada proximaCoordenada = viborita.getProximaUbicacion();
			Entidad entidadColision = obtenerEntidad(proximaCoordenada);
			if (entidadColision != null) {
				viborita.enColision(entidadColision);
				entidadColision.enColision(viborita);
			}
		}
	}
	
	public Entidad obtenerEntidad(Coordenada posicion) {
		return grilla[posicion.getX()][posicion.getY()];
	}
}
