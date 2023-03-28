package ma.fstm.ilisi2.libraryapp.model.dao;

import ma.fstm.ilisi2.libraryapp.model.bo.Exemplaire;
import ma.fstm.ilisi2.libraryapp.model.bo.Livre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class DAOLivre implements IDAOLivre {
    public DAOLivre() {

    }


    @Override
    public boolean create(Livre L) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(L);
            tx.commit();
            return true;
        }catch (Exception e){
            assert tx != null;
            tx.rollback();
            return false;
        }
    }


    public boolean create(Livre L, int nbrExemplaire) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(L);
            for (int i = 0; i < nbrExemplaire; i++) {
                session.persist(new Exemplaire(L,true));
            }
            tx.commit();
            return true;
        }catch (Exception e){
            assert tx != null;
            tx.rollback();
            return false;
        }
    }

    @Override
    public Collection<Livre> retrieve() {
        List<Livre> livres = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            livres = session.createQuery("from Livre l", Livre.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
            e.printStackTrace();
        }
        return livres;
    }

    @Override
    public boolean update(Livre L) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(L);
            tx.commit();
            return true;
        }catch (Exception e){
            assert tx != null;
            tx.rollback();
            return false;
        }
    }

    public boolean update(Livre L,int nbrExemplaire) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(L);
            for (int i = 0; i < nbrExemplaire; i++) {
                session.persist(new Exemplaire(L,true));
            }
            tx.commit();
            return true;
        }catch (Exception e){
            assert tx != null;
            tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Livre L) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.remove(L);
            System.out.println("deletion1 "+L.getIsbn());
            tx.commit();
            System.out.println("deletion2 "+L.getIsbn());
            return true;
        }catch (Exception e){
            assert tx != null;
            System.out.println("not deleted");
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
            tx.rollback();
            return false;
        }
    }

    @Override
    public Livre findById(int id) {
        Livre livre1 = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            livre1 = session.createQuery("from Livre l where l.id = :id", Livre.class).setParameter("id",id).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        return livre1;
    }

    public Livre findByISBN(String isbn) {
        Livre livre1 = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            livre1 = session.createQuery("from Livre l where l.isbn = :isbn", Livre.class).setParameter("isbn",isbn).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        return livre1;
    }

}

