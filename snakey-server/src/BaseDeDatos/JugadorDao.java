package BaseDeDatos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import Commons.Jugador;
import Comunicacion.Mensajes.LoginResponse;

public class JugadorDao extends Dao<Jugador, String> {

	public JugadorDao() {
		super(Jugador.class);
	}

	public boolean existenciaDeJugador(Jugador jugador) { 
		long cantidad = (long) getSession().createQuery(
				"select count(*) from Jugador j where j.nombreDeUsuario = '" + jugador.getNombreDeUsuario() + "' ")
				.uniqueResult();
		System.out.println(cantidad);
		if (cantidad == 1)
			return true;
		return false;
	}
	
	public Jugador getJugador(Jugador jugador) {
		Jugador jugadorDB = (Jugador)getSession().createQuery(
				"select j from Jugador j where j.nombreDeUsuario = '" + jugador.getNombreDeUsuario() + "' ");
		if(jugadorDB!=null) {
			jugadorDB.setClaveDeUsuario(null);
			return jugadorDB;
		}
		return null;
	}
	
	public boolean claveCorrecta(Jugador jugador) {
		long cantidad = (long) getSession().createQuery(
				"select count(*) from Jugador j where j.claveDeUsuario = '" + jugador.getClaveDeUsuario()+ "' and j.nombreDeUsuario = '" + jugador.getClaveDeUsuario() + "' ")
				.uniqueResult();
		System.out.println(cantidad);
		if (cantidad == 1)
			return true;
		return false;
	}

	public boolean insertarEnBdd(Jugador jugador) {
		if(!existenciaDeJugador(jugador)) {
			getSession().save(jugador);
			return true;
		}
		return false;
	}

	public void actualizarDatos(Jugador jugador) {
		getSession().update("UPDATE Jugador j SET j.partidasGanadas = jugador.partidasGanadas "
				+ "and j.partidasPerdidas = jugador.partidasPerdidas "
				+ "and j.puntajeAcumulado = jugador.puntajeAcumulado WHERE j.nombreDeUsuario = '"
				+ jugador.getNombreDeUsuario()+"'", jugador);
		return;
	}

	public void mostrarJugadores() {
		Query qu = getSession().createQuery("Select j from Jugador j");
		List<Jugador> listaJugadores = qu.getResultList();
		for (Jugador j : listaJugadores)
			System.out.println(j);
	}

}
