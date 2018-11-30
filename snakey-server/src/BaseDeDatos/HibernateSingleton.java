package BaseDeDatos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSingleton {
	public static SessionFactory factory;
	private HibernateSingleton() { }
	
	public static synchronized SessionFactory getSessionFactory() {
		if (factory == null) {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return factory;
	}
	
	public Session openSession() {
        return factory.openSession();
    }
	
	public static void cerrarConexion() {
		factory.close();
    }
}
