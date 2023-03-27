package ma.fstm.ilisi2.libraryapp.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class HibernateUtil {
    private static final SessionFactory ourSessionFactory;

    private static Session session;
    private static final String HIBERNATE_CONFIG_FILE = "hibernate.cfg.xml";
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure(HIBERNATE_CONFIG_FILE);

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return ourSessionFactory;
    }

    public static Session getSession() throws HibernateException {
        if (Objects.isNull(session))
            session = ourSessionFactory.openSession();
        return session;
    }

    public static void main(String[] args) {
        System.out.println(getSession());
    }


}
