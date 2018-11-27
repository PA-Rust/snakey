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
		System.out.println(cantidad);
		if (cantidad == 1)
			return true;
		return false;
	}
	
	public boolean ContraseñaCorrecta(Jugador jugador) {
		long cantidad = (long) getSession().createQuery(
				"select count(*) from Jugador j where j.claveDeUsuario = '" + jugador.getClaveDeUsuario() + "' and j.nombreDeUsuario = '" + jugador.getNombreDeUsuario() + "' ")
				.uniqueResult();
		System.out.println(cantidad);
		if (cantidad == 1)
			return true;
		return false;
	}

	public void insertarEnBdd(Jugador jugador) {
		if(!existenciaDeJugador(jugador)) 
			getSession().save(jugador);
		return;
	}

	public void actualizarDatos(Jugador jugador) {
		getSession().saveOrUpdate(jugador);
	}

	public void mostrarJugadores() {
		Query qu = getSession().createQuery("Select j from Jugador j");
		List<Jugador> listaJugadores = qu.getResultList();
		for (Jugador j : listaJugadores)
			System.out.println(j);
	}

}
