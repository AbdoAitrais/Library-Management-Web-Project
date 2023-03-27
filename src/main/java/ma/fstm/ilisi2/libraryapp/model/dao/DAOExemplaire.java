package ma.fstm.ilisi2.libraryapp.model.dao;

import ma.fstm.ilisi2.libraryapp.model.bo.Exemplaire;
import ma.fstm.ilisi2.libraryapp.model.bo.Livre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class DAOExemplaire implements IDAOExemplaire{
    @Override
    public boolean create(Exemplaire exemplaire) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(exemplaire);
            tx.commit();
            return true;
        }catch (Exception e){
            assert tx != null;
            tx.rollback();
            return false;
        }
    }

    @Override
    public Collection<Exemplaire> retrieve() {
        List<Exemplaire> exemplaires = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            exemplaires = session.createQuery("from Exemplaire e", Exemplaire.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
            e.printStackTrace();
        }
        return exemplaires;
    }

    @Override
    public boolean update(Exemplaire exemplaire) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(exemplaire);
            tx.commit();
            return true;
        }catch (Exception e){
            assert tx != null;
            tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Exemplaire exemplaire) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.remove(exemplaire);
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
    public Exemplaire findById(Long id) {
        Exemplaire exemplaire = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            exemplaire = session.createQuery("from Exemplaire e where e.id = :id", Exemplaire.class).setParameter("id",id).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        return exemplaire;
    }
}
