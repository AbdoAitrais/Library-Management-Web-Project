package ma.fstm.ilisi2.libraryapp.model.dao;

import ma.fstm.ilisi2.libraryapp.model.bo.Emprunt;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public class DAOEmprunt implements IDAOEmprunt{
    public DAOEmprunt() {
    }

    @Override
    public boolean create(Emprunt emprunt) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(emprunt);
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
    public Collection<Emprunt> retrieve() {
        List<Emprunt> emprunts = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            emprunts = session.createQuery("from Emprunt em", Emprunt.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        return emprunts;
    }

    @Override
    public boolean update(Emprunt emprunt) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(emprunt);
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
    public boolean delete(Emprunt emprunt) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Emprunt emprunt1 = new Emprunt();
            emprunt1.setId(emprunt.getId());
            session.remove(emprunt1);
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
    public Emprunt findEmpruntByID(Long id) {
        Emprunt emprunt1 = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            emprunt1 = session.createQuery("from Emprunt emp where emp.id = :id", Emprunt.class).setParameter("id",id).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        return emprunt1;
    }
}
