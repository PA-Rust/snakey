package BaseDeDatos;

import java.io.Serializable;

import javax.persistence.Query;

import org.hibernate.Session;


public abstract class Dao<C,ID extends Serializable> {
	///C->clase en cuestion
	///ID-> key de la clase en cuestion
	
	///sesion actual de hibernate
	private Session session;
	
	///clase generica,a la q el dao hace referencia
	private Class<C> clase;

	///las clases con correspondientes tablas en la bdd deben iniciar este constructor 
	protected Dao(Class<C> clase) {
        if (clase == null)
            throw new NullPointerException();
        this.clase = clase;
    }
	
	///retorna una sesion, creandola si no existe, o devolviendo la ya existente
	protected Session getSession() {
        if (session == null) {
            session = HibernateSingleton.getSessionFactory().openSession();
            session.beginTransaction();
        }
        return session;
    }
	
	protected void cerrarSession() {
        getSession().getTransaction().commit();
        getSession().close();
        session = null;
        System.out.println("sesion finalizada");
	}
	
	
}
