package Commons;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import Items.Item;
import Items.Manzana;
import Viborita.Cuerpo;
import Viborita.Viborita;

public class Mapa implements Serializable {
	private static final long serialVersionUID = -6285779595846322113L;
	private ArrayList<Viborita> viboritas;
	private Item item;
	private int ancho;
	private int alto;
	private Entidad[][] grilla;

	public Mapa(ArrayList<Jugador> jugadores) {
		viboritas = new ArrayList<Viborita>();
		for (int i = 0; i < jugadores.size(); i++) {
			viboritas.add(new Viborita(10 * (i + 1), jugadores.get(i)));
		}
		this.ancho = 50;
		this.alto = 50;
		grilla = new Entidad[ancho][alto];
		item = new Manzana(new Coordenada(45, 10), 1000);
		grilla[45][10] = item;
	}
	
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
	
	public void spawnearItem() {
		int randomX = ThreadLocalRandom.current().nextInt(0, ancho);
		int randomY = ThreadLocalRandom.current().nextInt(0, alto);
		Coordenada coordenadaRandom = new Coordenada(randomX, randomY);
		if (obtenerEntidad(coordenadaRandom) == null) {
			item = new Manzana(coordenadaRandom, 300);
			grilla[randomX][randomY] = item;
		} else {
			spawnearItem();
		}
	}
	
	public synchronized void actualizar() {
		if (item.getReloj() == 0) {
			spawnearItem();
		} else {
			item.decrementarReloj(1);
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
		viboritas.remove(viborita);
	}
	
	/*
	 * Expected to have a squared panel to draw on.
	 */
	public void dibujar(Graphics graphics, Map<Avatar, Image> imagenes, int tamBloque) {
		for (Viborita viborita: viboritas) {
			for (Cuerpo cuerpo: viborita.getCuerpo()) {
				graphics.drawImage(
					imagenes.get(viborita.getJugador().getAvatar()),
					cuerpo.getPosicion().getX() * tamBloque,
					cuerpo.getPosicion().getY() * tamBloque,
					tamBloque, tamBloque, null
				);
			}
		}

		graphics.drawImage(
			imagenes.get(item.getAvatar()),
			item.getPosicion().getX() * tamBloque,
			item.getPosicion().getY() * tamBloque,
			tamBloque, tamBloque, null
		);
	}
	
	public void actualizarViboritas() {
		for (Viborita viborita: viboritas) {
			viborita.actualizar();
		}
	}
	
	public void reubicarViboritas() {
		for (Iterator<Viborita> iterator = viboritas.iterator(); iterator.hasNext(); ) {
			Viborita viborita = iterator.next();
			if (!viborita.estaViva()) {
				iterator.remove();
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
		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < alto; y++) {
				grilla[x][y] = null;
			}
		}
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
