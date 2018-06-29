package dao.hibernate;

import java.io.File;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public final class HibernateUtil {
	
	private static Logger logger = Logger.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;
    

    private static ThreadLocal<Session> sessions = new ThreadLocal<Session>();

    static
    {
    	// Define estrat;egia de nome de banco tabela e coluna
    	AnnotationConfiguration conf = new AnnotationConfiguration();
    	conf.setNamingStrategy(ImprovedNamingStrategy.INSTANCE);
        //Ler hibernate.cfg.xml
    	HibernateUtil.sessionFactory = conf.configure().buildSessionFactory();
    }
    
    /**
     * @return Session
     */
    public static Session openSession()
    {

        if (HibernateUtil.sessions.get() != null)
        {
        	HibernateUtil.logger.error("A sessão para está thread ja está aberta!! "); //$NON-NLS-1$
        }
        HibernateUtil.sessions.set(HibernateUtil.sessionFactory.openSession());

        return HibernateUtil.sessions.get();
    }

    /**
     * Fecha a Session corrente
     */
    public static void closeCurrentSession()
    {
    	HibernateUtil.sessions.get().close();
    	HibernateUtil.sessions.set(null);
    }

    /**
     * @return a Session corrente
     */
    public static Session currentSession()
    {
        return HibernateUtil.sessions.get();
    }

    /**
     * Apaga o banco existente (se tiver) e recria com base na configuração do Hibernate.
     * @param configFilePath ex: "src/test/resources/hibernate.cfg2.xml"
     */
    public static void geraBanco(final String configFilePath)
    {
        //Cria uma configuraçao
        final AnnotationConfiguration configuration = new AnnotationConfiguration();
        //Lê o hibernate.cfg.xml
        if (configFilePath == null)
        {
            configuration.configure();
        }
        else
        {
            configuration.configure(new File(configFilePath));
        }
        final SchemaExport se = new SchemaExport(configuration);
        se.create(true, true);
    }

    /**
     * Apaga o banco existente.
     * @param configFilePath ex: "src/test/resources/hibernate.cfg2.xml"
     */
    public static void apagaBanco(final String configFilePath)
    {
        //Cria uma configuraçao
        final AnnotationConfiguration configuration = new AnnotationConfiguration();
        //Lê o hibernate.cfg.xml
        if (configFilePath == null)
        {
            configuration.configure();
        }
        else
        {
            configuration.configure(new File(configFilePath));
        }
        final SchemaExport se = new SchemaExport(configuration);
        se.drop(true, true);
    }
}

