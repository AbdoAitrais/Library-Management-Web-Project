package ma.fstm.ilisi2.libraryapp.model.dao;


import ma.fstm.ilisi2.libraryapp.model.bo.Adherent;

import java.math.BigInteger;
import java.util.Collection;

public interface IDAOAdherent {
    boolean create(Adherent adherent);
    Collection<Adherent> retrieve();
    boolean update(Adherent adherent);
    boolean delete(Adherent adherent);
    Adherent findAdherentByID(Long id);
}
