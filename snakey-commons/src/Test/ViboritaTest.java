package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sun.javafx.scene.traversal.Direction;

import Commons.Coordenada;
import Commons.Direccion;
import Commons.Entidad;
import Commons.Jugador;
import Commons.Mapa;
import Items.Manzana;
import Viborita.Viborita;
import junit.framework.Assert;

public class ViboritaTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testEntreColisionCabezas() {
		int posicion = 0;

		Viborita viboritaUno = new Viborita(posicion, new Jugador());
		Viborita viboritaDos = new Viborita(posicion, new Jugador());

		/*
		 * Caso 1 : Dos viboritas en la misma posicion ambas. Deberían morir las 2:
		 */
		viboritaUno.enColision(viboritaDos.getCabeza());
		// tengo dudas si no seria mejor que en una sola llamada murieran las dos
		viboritaDos.enColision(viboritaUno.getCabeza());
		boolean vidaUno = viboritaUno.estaViva();
		boolean vidaDos = viboritaDos.estaViva();
		Assert.assertEquals(false, vidaUno);
		Assert.assertEquals(false, vidaDos);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testEntreColisionCabezaYCuerpo() {
		int posicion = 0;
		boolean vidaUno;
		Viborita viboritaUno = new Viborita(posicion, new Jugador());
		Viborita viboritaDos = new Viborita(posicion, new Jugador());
		/*
		 * Caso 2: La cabeza de una viborita en la posicion de un cuerpo
		 */
		viboritaUno = new Viborita(posicion, new Jugador());
		vidaUno = viboritaUno.estaViva();
		Assert.assertEquals(true, vidaUno);
		viboritaUno.enColision(viboritaDos.getCuerpo().get(2));
		vidaUno = viboritaUno.estaViva();
		Assert.assertEquals(false, vidaUno);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testEntreColisionCabezaYManzana() {
		int posicion = 0;
		Viborita viboritaUno = new Viborita(posicion, new Jugador());
		/*
		 * Caso 3 : Una viborita choca contra una manzanita, no tiene que morir
		 */
		boolean vidaUno;
		viboritaUno = new Viborita(posicion, new Jugador());
		vidaUno = viboritaUno.estaViva();
		Assert.assertEquals(true, vidaUno);
		Manzana manzanita = new Manzana(viboritaUno.getCabeza().getPosicion());
		viboritaUno.enColision(manzanita);
		vidaUno = viboritaUno.estaViva();
		Assert.assertEquals(true, vidaUno);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCambiarDireccion() {
		int pos = 4;
		Viborita vib = new Viborita(pos, new Jugador());
		vib.getCabeza().setDireccion(Direccion.arriba);
		// lo seteo con direccion hacia arriba
		Direccion dirUno = vib.getCabeza().getDireccion();

		/*
		 * Caso 1: Si la direccion nueva esta en el mismo eje que la direccion no cambio
		 * nada (EJE VERTICAL)
		 */

		vib.cambiarDireccion(Direccion.arriba);
		Assert.assertEquals(dirUno, vib.getCabeza().getDireccion());
		vib.cambiarDireccion(Direccion.abajo);
		Assert.assertEquals(dirUno, vib.getCabeza().getDireccion());

		vib.getCabeza().setDireccion(Direccion.derecha);
		dirUno = vib.getCabeza().getDireccion();

		/*
		 * Caso 2: Si la direccion nueva esta en el mismo eje que la direccion no cambio
		 * nada (EJE HORIZONTAL)
		 */

		vib.cambiarDireccion(Direccion.derecha);
		Assert.assertEquals(dirUno, vib.getCabeza().getDireccion());
		vib.cambiarDireccion(Direccion.izquierda);
		Assert.assertEquals(dirUno, vib.getCabeza().getDireccion());

		/*
		 * Caso 3: Si la direccion nueva no esta en el mismo eje que la direccion no
		 * cambio nada (EJE HORIZONTAL)
		 */

		vib.cambiarDireccion(Direccion.arriba);
		Assert.assertNotSame(vib.getCabeza().getDireccion(), dirUno);
		dirUno = vib.getCabeza().getDireccion();

		vib.cambiarDireccion(Direccion.izquierda);
		Assert.assertNotSame(vib.getCabeza().getDireccion(), dirUno);
		dirUno = vib.getCabeza().getDireccion();

		vib.cambiarDireccion(Direccion.abajo);
		Assert.assertNotSame(vib.getCabeza().getDireccion(), dirUno);
		dirUno = vib.getCabeza().getDireccion();

		vib.cambiarDireccion(Direccion.derecha);
		Assert.assertNotSame(vib.getCabeza().getDireccion(), dirUno);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetProximaUbicacion() {
		Mapa map = new Mapa(new Jugador[1]);

		Viborita viborita = new Viborita(map.getAlto() / 2, new Jugador());
		Coordenada coord = viborita.getCabeza().getPosicion();
		/*
		 * Si la viborita se va de los contornos del mapa aparece del otro lado (en
		 * mapa)
		 */
		/*
		 * Caso 1: Avanza uno en la direccion que tenga la cabeza DIRECCION ARRIBA
		 */

		viborita.cambiarDireccion(Direccion.abajo);
		viborita.desplazar();
		System.out.println(viborita.getCabeza().getPosicion().getX() + " " + viborita.getCabeza().getPosicion().getY());
		Assert.assertEquals(true, viborita.getCabeza().getPosicion().getY() == coord.getY() + 1);
		coord = viborita.getCabeza().getPosicion();
		/*
		 * Caso 2: Avanza uno en la direccion que tenga la cabeza DIRECCION IZQUIERDA
		 */
		viborita.cambiarDireccion(Direccion.izquierda);
		viborita.desplazar();
		System.out.println(viborita.getCabeza().getPosicion().getX() + " " + viborita.getCabeza().getPosicion().getY());
		Assert.assertEquals(true, viborita.getCabeza().getPosicion().getX() == coord.getX() - 1);
		coord = viborita.getCabeza().getPosicion();
		/*
		 * Caso 3: Avanza uno en la direccion que tenga la cabeza DIRECCION ABAJO
		 */

		viborita.cambiarDireccion(Direccion.arriba);
		viborita.desplazar();
		System.out.println(viborita.getCabeza().getPosicion().getX() + " " + viborita.getCabeza().getPosicion().getY());
		Assert.assertEquals(true, viborita.getCabeza().getPosicion().getY() == coord.getY() - 1);
		coord = viborita.getCabeza().getPosicion();
		/*
		 * Caso 4: Avanza uno en la direccion que tenga la cabeza DIRECCION DERECHA
		 */

		viborita.cambiarDireccion(Direccion.derecha);
		viborita.desplazar();
		System.out.println(viborita.getCabeza().getPosicion().getX() + " " + viborita.getCabeza().getPosicion().getY());

		Assert.assertEquals(true, viborita.getCabeza().getPosicion().getX() == coord.getX() + 1);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testWarpEnX() {
		/*
		 * Mapa de Alto y Ancho de 100*100 Entrea por la izquierda y aparece por la
		 * derecha
		 */
		Mapa mapa = new Mapa(new Jugador[1]);
		int posicion = 5;
		Viborita viboritaUno = new Viborita(posicion, new Jugador());
		viboritaUno.getCabeza().setPosicion(new Coordenada(-1, 5));

		assertEquals(mapa.getAncho() - 1,
				viboritaUno.getCabeza().getPosicion().warp(new Coordenada(mapa.getAncho(), mapa.getAlto())).getX());

		/*
		 * Entra por la derecha y entra por la izquierda
		 */

		viboritaUno.getCabeza().setPosicion(new Coordenada(101, 5));
		assertEquals(0,
				viboritaUno.getCabeza().getPosicion().warp(new Coordenada(mapa.getAncho(), mapa.getAlto())).getX());

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testWarpEnY() {
		Mapa mapa = new Mapa(new Jugador[1]);
		int posicion = -1;
		// Ingresa por abajo y aparece por arriba
		Viborita viboritaUno = new Viborita(posicion, new Jugador());
		viboritaUno.getCabeza().getPosicion().setX(7);
		assertEquals(mapa.getAlto() - 1,
				viboritaUno.getCabeza().getPosicion().warp(new Coordenada(mapa.getAncho(), mapa.getAlto())).getY());

		// Ingresa por arriba y aparece por abajo

		viboritaUno.getCabeza().getPosicion().setY(101);
		assertEquals(0,
				viboritaUno.getCabeza().getPosicion().warp(new Coordenada(mapa.getAncho(), mapa.getAlto())).getY());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGenerandoMapaYCreacionVivoritas() {
		Jugador[] jugadores = new Jugador[2];
		jugadores[0] = new Jugador();
		jugadores[1] = new Jugador();
		Mapa map = new Mapa(jugadores);

		assertEquals(true, map.getViboritas().get(0).getCabeza().getPosicion().equals(new Coordenada(4, 10)));
		assertEquals(true, map.getViboritas().get(1).getCabeza().getPosicion().equals(new Coordenada(4, 20)));
	}

}
