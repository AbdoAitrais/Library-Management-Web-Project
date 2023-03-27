package ma.fstm.ilisi2.libraryapp.model.dao;

import ma.fstm.ilisi2.libraryapp.model.bo.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.List;

public class DAOAccount implements IDAOAccount {
    @Override
    public boolean create(Account account) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(account);
            System.out.println(account.getUsername());
            session.flush();
            tx.commit();
            return true;
        }catch (Exception e){
            assert tx != null;
            System.out.println("error inserting");
            tx.rollback();
            return false;
        }
    }

    @Override
    public Collection<Account> retrieve() {
        List<Account> accounts = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            accounts = session.createQuery("from Account a", Account.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public boolean update(Account account) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(account);
            tx.commit();
            return true;
        }catch (Exception e){
            assert tx != null;
            tx.rollback();
            return false;
        }
    }

    @Override
    public boolean delete(Account account) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Account account1 = new Account();
            account1.setId(account.getId());
            session.remove(account1);
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
    public Account findById(Long id) {
        Account account1 = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            account1 = session.createQuery("from Account a where a.id = :id", Account.class).setParameter("id",id).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        return account1;
    }

    @Override
    public Account find(String username, String password) {
        Account account1 = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            account1 = session.createQuery("from Account a where a.username = :usr and a.password = :pwd", Account.class).setParameter("usr",username).setParameter("pwd",password).getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
        return account1;
    }
}
