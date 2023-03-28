package ma.fstm.ilisi2.libraryapp.model.services;

import ma.fstm.ilisi2.libraryapp.model.bo.Adherent;
import ma.fstm.ilisi2.libraryapp.model.dao.DAOAdherent;

import java.util.List;

public class ServiceAdherent {
    private final DAOAdherent daoAdherent;

    public ServiceAdherent() {
        this.daoAdherent = new DAOAdherent();
    }


    public List<Adherent> getAllAdherents() {
        return (List<Adherent>) daoAdherent.retrieve();
    }

    public Adherent getAdherentById(int idAdherent) {
        return daoAdherent.findAdherentByID((long) idAdherent);
    }

    public void createAdherent(String nom, String prenom) {
        Adherent adherent = new Adherent();
        adherent.setNomAdherent(nom);
        adherent.setPrenomAdherent(prenom);
        daoAdherent.create(adherent);
    }

    public void updateAdherent(int idAdherent, String nom, String prenom) {
        Adherent adherent = daoAdherent.findAdherentByID((long) idAdherent);
        adherent.setNomAdherent(nom);
        adherent.setPrenomAdherent(prenom);
        daoAdherent.update(adherent);
    }

    public void deleteAdherent(int idAdherent) {
        Adherent adherent = daoAdherent.findAdherentByID((long) idAdherent);
        daoAdherent.delete(adherent);
    }
}
