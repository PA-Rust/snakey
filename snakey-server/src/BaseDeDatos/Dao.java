package BaseDeDatos;

import java.io.Serializable;

import javax.persistence.Query;

import org.hibernate.Session;


public abstract class Dao<C,ID extends Serializable> {
	
	private Session session;
	
	private Class<C> clase;
 
	protected Dao(Class<C> clase) {
        if (clase == null)
            throw new NullPointerException();
        this.clase = clase;
    }
	
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
