package ma.fstm.ilisi2.libraryapp.model.dao;


import ma.fstm.ilisi2.libraryapp.model.bo.Account;

import java.util.Collection;

public interface IDAOAccount {
    boolean create(Account account);
    Collection<Account> retrieve();
    boolean update(Account account);
    boolean delete(Account account);
    Account findById(Long id);
    Account find(String username,String password);
}
