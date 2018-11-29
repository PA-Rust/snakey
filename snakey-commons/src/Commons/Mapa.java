package Commons;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import Items.Item;
import Items.Manzana;
import Viborita.Cuerpo;
import Viborita.Viborita;

public class Mapa {
	private ArrayList<Viborita> viboritas;
	private Item item;
	private int ancho;
	private int alto;
	private Entidad[][] grilla;
	/*El mapa es de 50 x 50,
	 * con una sola manzanita en la posicion 45 10 (?*/
	public Mapa(Jugador[] jugadores) {
		viboritas = new ArrayList<Viborita>();
		for (int i = 0; i < jugadores.length; i++) {
			viboritas.add(new Viborita(10 * (i + 1), jugadores[i]));
		}
		this.ancho = 50;
		this.alto = 50;
		grilla = new Entidad[ancho][alto];
		item = new Manzana(new Coordenada(45, 10), 1000);
		grilla[45][10] = item;
	}
	/*Inicializa la grilla poniendala toda en null*/
	public void inicializarGrilla() {
		for (int i = 0; i < ancho; i++) {
			for (int j = 0; j < alto; j++) {
				grilla[i][j] = null;
			}
		}
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
/*Creo una coordenada aleatoria,
 * y si la coordenada esa esta vacia, instancio una manzanita alli
 * sino vuelvo a llamar a este metodo*/	
	public void spawnearItem() {
		int randomX = ThreadLocalRandom.current().nextInt(0, ancho + 1);
		int randomY = ThreadLocalRandom.current().nextInt(0, alto + 1);
		Coordenada coordenadaRandom = new Coordenada(randomX, randomY);
		if (obtenerEntidad(coordenadaRandom) == null) {
			item = new Manzana(coordenadaRandom, 600);
			grilla[randomX][randomY] = item;
		} else {
			spawnearItem();
		}
	}
/*Todo el mapa depende del reloj del item(?
 * 
 * */	
	public void actualizar() {
		if (item.getReloj() == 0) {
			spawnearItem();
		} else {
			item.decrementarReloj(10);
		}
		limpiarGrilla();
		actualizarViboritas();
		reubicarViboritas();
		chequearColisiones();
	}
	
	public ArrayList<Viborita> getViboritas() {
		return viboritas;
	}
	
	public void removerViborita(Viborita viborita) {
		// TODO(toti): Remover la viborita de la grilla inmediatamente
		// para no tenerla en los calculos de otra viborita.
		viboritas.remove(viborita);
	}
	
	/*
	 * Expected to have a squared panel to draw on.
	 */
	public void dibujar(Graphics graphics, Map<Avatar, Image> imagenes, int screenSize, int tamCuadrado) {
		for (Viborita viborita: viboritas) {
			for (Cuerpo cuerpo: viborita.getCuerpo()) {
				graphics.drawImage(
					imagenes.get(viborita.getAvatar()),
					cuerpo.getPosicion().getX() * tamCuadrado,
					cuerpo.getPosicion().getY() * tamCuadrado,
					tamCuadrado, tamCuadrado, null);
			}
		}

		graphics.drawImage(
				imagenes.get(item.getAvatar()),
				item.getPosicion().getX() * tamCuadrado,
				item.getPosicion().getY() * tamCuadrado,
				tamCuadrado, tamCuadrado, null);
	}
	
	public void actualizarViboritas() {
		for (Viborita viborita: viboritas) {
			viborita.actualizar();
		}
	}
	
	public void reubicarViboritas() {
		for (Viborita viborita: viboritas) {
			if (!viborita.estaViva()) {
				removerViborita(viborita);
			}
			for (Cuerpo parte: viborita.getCuerpo()) {
				Coordenada coordenadaParte =
					parte.getPosicion().warp(new Coordenada(ancho, alto));
				parte.setPosicion(coordenadaParte);
				grilla[coordenadaParte.getX()][coordenadaParte.getY()] = parte;
			}
		}
	}
	
	
	public void limpiarGrilla() {
		inicializarGrilla();
		Coordenada coordenadaItem = item.getPosicion();
		grilla[coordenadaItem.getX()][coordenadaItem.getY()] = item;
	}
	
	public void chequearColisiones() {
		for (Viborita viborita: viboritas) {
			Coordenada proximaCoordenada = viborita.getProximaUbicacion().warp(new Coordenada(ancho, alto));
			Entidad entidadColision = obtenerEntidad(proximaCoordenada);
			if (entidadColision != null) {
				viborita.enColision(entidadColision);
				entidadColision.enColision(viborita);
			}
		}
	}
	
	public Entidad[][] getGrilla() {
		return grilla;
	}
	
	public Entidad obtenerEntidad(Coordenada posicion) {
		return grilla[posicion.getX()][posicion.getY()];
	}
}
