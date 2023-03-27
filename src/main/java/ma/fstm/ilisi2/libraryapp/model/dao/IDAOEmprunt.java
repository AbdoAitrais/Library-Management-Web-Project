package ma.fstm.ilisi2.libraryapp.model.dao;


import ma.fstm.ilisi2.libraryapp.model.bo.Emprunt;

import java.util.Collection;

public interface IDAOEmprunt {
    boolean create( Emprunt emprunt);
    Collection<Emprunt> retrieve();
    boolean update(Emprunt emprunt);
    boolean delete(Emprunt emprunt);
    Emprunt findEmpruntByID(Long id);
}
