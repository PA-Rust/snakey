package Test;

import static org.junit.Assert.*;


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
	public void testEnColision() {
		int posicion = 0; 
		
		Viborita viboritaUno= new Viborita(posicion, new Jugador());
		Viborita viboritaDos= new Viborita(posicion,new Jugador());
		
		/*Caso 1 : 
		 * Dos viboritas en la misma posicion ambas.
		 * Deberían morir las 2:*/
		viboritaUno.enColision(viboritaDos.getCabeza());
		//tengo dudas si no seria mejor que en una sola llamada murieran las dos 
		viboritaDos.enColision(viboritaUno.getCabeza());
		boolean vidaUno =viboritaUno.estaViva();
		boolean vidaDos = viboritaDos.estaViva();
		Assert.assertEquals(false,vidaUno );
		Assert.assertEquals(false, vidaDos);
	/*Caso 2:
	 * La cabeza de una viborita en la posicion de un cuerpo 
	 * */
		viboritaUno= new Viborita(posicion, new Jugador());
		vidaUno= viboritaUno.estaViva();
		Assert.assertEquals(true,vidaUno );
		viboritaUno.enColision(viboritaDos.getCuerpo().get(2));
		vidaUno= viboritaUno.estaViva();
		Assert.assertEquals(false,vidaUno );
		
		/*Caso 3 :
		 * Una viborita choca contra una manzanita, no tiene que morir */
		viboritaUno = new Viborita(posicion,new Jugador());
		vidaUno= viboritaUno.estaViva();
		Assert.assertEquals(true,vidaUno );
		Manzana manzanita = new Manzana(viboritaUno.getCabeza().getPosicion());
		viboritaUno.enColision(manzanita);
		vidaUno= viboritaUno.estaViva();
		Assert.assertEquals(true,vidaUno );
		
	}

	@Test
	public void testCambiarDireccion() {
		int pos= 4;
		Viborita vib= new Viborita(pos, new Jugador());
		vib.getCabeza().setDireccion(Direccion.arriba);
		//lo seteo con direccion hacia arriba
		Direccion dirUno= vib.getCabeza().getDireccion();

		/*Caso 1: Si la direccion  nueva esta en el mismo eje que la direccion
		 * no cambio nada
		 * (EJE VERTICAL) */
		
		vib.cambiarDireccion(Direccion.arriba);
		Assert.assertEquals(dirUno,vib.getCabeza().getDireccion( ));
		vib.cambiarDireccion(Direccion.abajo);
		Assert.assertEquals(dirUno,vib.getCabeza().getDireccion( ));
		
		vib.getCabeza().setDireccion(Direccion.derecha);
		dirUno= vib.getCabeza().getDireccion();
		
		/*Caso 2: Si la direccion  nueva esta en el mismo eje que la direccion
		 * no cambio nada
		 * (EJE HORIZONTAL) */
		
		vib.cambiarDireccion(Direccion.derecha);
		Assert.assertEquals(dirUno,vib.getCabeza().getDireccion( ));
		vib.cambiarDireccion(Direccion.izquierda);
		Assert.assertEquals(dirUno,vib.getCabeza().getDireccion( ));
		
		/*Caso 3: Si la direccion  nueva no esta en el mismo eje que la direccion
		 * no cambio nada
		 * (EJE HORIZONTAL) */
		
		vib.cambiarDireccion(Direccion.arriba);
		Assert.assertNotSame(vib.getCabeza().getDireccion(), dirUno);
		dirUno= vib.getCabeza().getDireccion();
		
		vib.cambiarDireccion(Direccion.izquierda);
		Assert.assertNotSame(vib.getCabeza().getDireccion(), dirUno);
		dirUno= vib.getCabeza().getDireccion();
		
		vib.cambiarDireccion(Direccion.abajo);
		Assert.assertNotSame(vib.getCabeza().getDireccion(), dirUno);
		dirUno= vib.getCabeza().getDireccion();
		
		vib.cambiarDireccion(Direccion.derecha);
		Assert.assertNotSame(vib.getCabeza().getDireccion(), dirUno);
		
		
		
		
		
		
		
	}
	@Test
	public void testGetProximaUbicacion() {
		Mapa map= new Mapa(new Jugador[1]);
		
		Viborita viborita = new Viborita(map.getAlto()/2, new Jugador());
        Coordenada coord= viborita.getCabeza().getPosicion();
		/* Si la viborita se va de los contornos del mapa aparece del otro lado (en mapa)*/
		/*Caso 1:
		 * Avanza uno en la direccion que tenga la cabeza
		 * DIRECCION ARRIBA*/
		
		viborita.cambiarDireccion(Direccion.abajo);
		viborita.desplazar();
		System.out.println(viborita.getCabeza().getPosicion().getX()+" "+viborita.getCabeza().getPosicion().getY());
		Assert.assertEquals(true,viborita.getCabeza().getPosicion().getY()==coord.getY()+1);
		 coord= viborita.getCabeza().getPosicion();
		/*Caso 2:
		 * Avanza uno en la direccion que tenga la cabeza
		 * DIRECCION IZQUIERDA*/
		viborita.cambiarDireccion(Direccion.izquierda);
		viborita.desplazar();
		System.out.println(viborita.getCabeza().getPosicion().getX()+" "+viborita.getCabeza().getPosicion().getY());
		Assert.assertEquals(true,viborita.getCabeza().getPosicion().getX()==coord.getX()-1);
		 coord= viborita.getCabeza().getPosicion();
		/*Caso 3:
		 * Avanza uno en la direccion que tenga la cabeza
		 * DIRECCION ABAJO*/

		viborita.cambiarDireccion(Direccion.arriba);
		viborita.desplazar();
		System.out.println(viborita.getCabeza().getPosicion().getX()+" "+viborita.getCabeza().getPosicion().getY());
		Assert.assertEquals(true,viborita.getCabeza().getPosicion().getY()==coord.getY()-1);
		 coord= viborita.getCabeza().getPosicion();
		/*Caso 4:
		 * Avanza uno en la direccion que tenga la cabeza
		 * DIRECCION DERECHA*/

		viborita.cambiarDireccion(Direccion.derecha);
		viborita.desplazar();
		System.out.println(viborita.getCabeza().getPosicion().getX()+" "+viborita.getCabeza().getPosicion().getY());
		
		Assert.assertEquals(true,viborita.getCabeza().getPosicion().getX()==coord.getX()+1);
	}
	/*@Test
	public void testGetCabeza() {
		
	
	}

	
	@Test+
	public void testGetCuerpo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVelocidad() {
		fail("Not yet implemented");
	}

	@Test
	public void testDesplazar() {
		fail("Not yet implemented");
	}

	
*/

}
