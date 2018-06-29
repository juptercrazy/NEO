package dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class Dao {
	
	private static final Logger log = Logger.getAnonymousLogger();
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	private static final SessionFactory sessionFactory = new AnnotationConfiguration()
			.configure().buildSessionFactory();

	protected Dao() {
	}

	public static Session getSession() {
		Session session = (Session) Dao.session.get();
		if (session == null) {
			session = sessionFactory.openSession();
			Dao.session.set(session);
		}
		return session;
	}

	protected void begin() {
		getSession().beginTransaction();
	}

	protected void commit() {
		getSession().getTransaction().commit();
	}
	
	protected void rollback() {
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Falha no rollback", e );
		}
		
		try {
			getSession().close();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Falha ao fechar a conexao", e);
		}
		Dao.session.set(null);
	}
	
	public static void close() {
		getSession().close();
		Dao.session.set(null);
	}
	
//	public static List<?> createListQuery(String query) {
//		try {
//			return getSession().createQuery(query).list();
//		} catch (HibernateException e) {
//			log.log(Level.WARNING, "Falha no select", e );
//		}
//		return null;
//	}
}
