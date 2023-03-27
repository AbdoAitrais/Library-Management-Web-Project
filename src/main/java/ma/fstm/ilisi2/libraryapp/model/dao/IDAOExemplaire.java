package ma.fstm.ilisi2.libraryapp.model.dao;

import ma.fstm.ilisi2.libraryapp.model.bo.Exemplaire;
import ma.fstm.ilisi2.libraryapp.model.bo.Livre;

import java.util.Collection;

public interface IDAOExemplaire {
    boolean create(Exemplaire exemplaire);
    Collection<Exemplaire> retrieve();
    boolean update(Exemplaire exemplaire);
    boolean delete(Exemplaire exemplaire);
    Exemplaire findById(Long id);
}
