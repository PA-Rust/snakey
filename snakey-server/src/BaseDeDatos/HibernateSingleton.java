package BaseDeDatos;

import java.util.ArrayList;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import Commons.Jugador;
import java.util.List;

public class HibernateSingleton {
	public static SessionFactory factory;
//	private static HibernateSingleton instancia;
	private HibernateSingleton() {
		
	}
	
	///crea instancia de singleton una unica vez,establece la coneccion con la bdd
	public static synchronized SessionFactory getSessionFactory() {

		if (factory == null) {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return factory;
	}
	
	///abre sesion
	public Session openSession() {
        return factory.openSession();
    }
	
	///cierra sesion
	public static void cerrarConexion() {
		factory.close();
    }
	
}
