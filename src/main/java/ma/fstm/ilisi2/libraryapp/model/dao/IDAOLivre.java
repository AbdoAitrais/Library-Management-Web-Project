package ma.fstm.ilisi2.libraryapp.model.dao;


import ma.fstm.ilisi2.libraryapp.model.bo.Livre;

import java.util.Collection;

public interface IDAOLivre {
    boolean create(Livre L);
    Collection<Livre> retrieve();
    boolean update(Livre L);
    boolean delete(Livre L);
    Livre findById(int id);
}
