package ma.fstm.ilisi2.libraryapp.model.dao;

import ma.fstm.ilisi2.libraryapp.model.bo.Adherent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public class DAOAdherent implements IDAOAdherent{
    public DAOAdherent() {
    }

    @Override
    public boolean create(Adherent adherent) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(adherent);
            session.flush();
            tx.commit();
            return true;
        }catch (Exception e){
            assert tx != null;
            tx.rollback();
            return false;
        }
    }

    @Override
    public Collection<Adherent> retrieve() {
        List<Adherent> adherents = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            adherents = session.createQuery("from Adherent a", Adherent.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        return adherents;
    }

    @Override
    public boolean update(Adherent adherent) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(adherent);
            session.flush();
            tx.commit();
            return true;
        }catch (Exception e){
            assert tx != null;
            tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Adherent adherent) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Adherent adherent1 = new Adherent();
            adherent1.setId(adherent.getId());
            session.remove(adherent1);
            session.flush();
            tx.commit();
            return true;
        }catch (Exception e){
            assert tx != null;
            tx.rollback();
            return false;
        }
    }

    @Override
    public Adherent findAdherentByID(Long id) {
        Adherent adherent1 = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            adherent1 = session.createQuery("from Adherent a where a.id = :id", Adherent.class).setParameter("id",id).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        return adherent1;
    }
}
