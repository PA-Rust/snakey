package BaseDeDatos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import Commons.Jugador;

public class JugadorDao extends Dao<Jugador, String> {

	public JugadorDao() {
		super(Jugador.class);
	}

	public boolean existenciaDeJugador(Jugador jugador) { 
		long cantidad = (long) getSession().createQuery(
				"select count(*) from Jugador j where j.nombreDeUsuario = '" + jugador.getNombreDeUsuario() + "' ")
				.uniqueResult();
		if (cantidad == 1) 
			return true;
		return false;
	}
	
	public Jugador getJugador(Jugador jugador) {
		Jugador jugadorDB = (Jugador) getSession().createQuery(
				"select j from Jugador j where j.nombreDeUsuario = '" + jugador.getNombreDeUsuario() + "' ").uniqueResult();
		getSession().getTransaction().commit();
		if(jugadorDB!=null) {
			return jugadorDB;
		}
		return null;
	}
	
	public boolean claveCorrecta(Jugador jugador) {
		long cantidad = (long) getSession().createQuery(
				"select count(*) from Jugador j where j.claveDeUsuario = '" + jugador.getClaveDeUsuario()+ "' and j.nombreDeUsuario = '" + jugador.getNombreDeUsuario() + "' ")
				.uniqueResult();
		if (cantidad == 1) {
			getSession().flush();
			return true;
		}
		return false;
	}

	public boolean insertarEnBdd(Jugador jugador) {
		if(!existenciaDeJugador(jugador)) {
			getSession().save(jugador);
			getSession().getTransaction().commit();
			return true;
		}
		getSession().getTransaction().commit();
		return false;
	}

	public void actualizarDatos(Jugador jugador) {
		if (!getSession().getTransaction().isActive()) {
			getSession().getTransaction().begin();
		}
		getSession().update("UPDATE Jugador j SET j.partidasGanadas = jugador.partidasGanadas "
				+ "and j.partidasPerdidas = jugador.partidasPerdidas "
				+ "and j.puntajeAcumulado = jugador.puntajeAcumulado WHERE j.nombreDeUsuario = '"
				+ jugador.getNombreDeUsuario()+"'", jugador);
		getSession().getTransaction().commit();
		return;
	}

	public void mostrarJugadores() {
		Query qu = getSession().createQuery("Select j from Jugador j");
		List<Jugador> listaJugadores = qu.getResultList();
		for (Jugador j : listaJugadores)
			System.out.println(j);
	}

}
