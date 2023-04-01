package ma.fstm.ilisi2.libraryapp.model.services;

import ma.fstm.ilisi2.libraryapp.model.bo.Emprunt;
import ma.fstm.ilisi2.libraryapp.model.bo.Exemplaire;
import ma.fstm.ilisi2.libraryapp.model.dao.DAOAdherent;
import ma.fstm.ilisi2.libraryapp.model.dao.DAOEmprunt;
import ma.fstm.ilisi2.libraryapp.model.dao.DAOExemplaire;
import ma.fstm.ilisi2.libraryapp.model.dao.DAOLivre;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ServiceEmprunt {
    private final DAOEmprunt daoEmprunt;
    private final DAOExemplaire daoExemplaire;
    private final ServiceAdherent serviceAdherent;
    private final ServiceLivre serviceLivre;

    public ServiceEmprunt() {
        this.daoEmprunt = new DAOEmprunt();
        this.daoExemplaire = new DAOExemplaire();
        this.serviceAdherent = new ServiceAdherent();
        this.serviceLivre = new ServiceLivre();
    }

    public List<Emprunt> getAllEmprunts() {
        return (List<Emprunt>) daoEmprunt.retrieve();
    }

    public void createEmprunt(int idLivre, int idAdherent, String dateRetourStr) {
        Emprunt emprunt = new Emprunt();
        Exemplaire e = serviceLivre.retrieveExemplaireDispo(idLivre);

        emprunt.setExemplaire(e);
        emprunt.setAdherent(serviceAdherent.getAdherentById(idAdherent));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateRetour = LocalDate.parse(dateRetourStr, formatter);

        emprunt.setDateEmprunt(new java.sql.Date(new Date().getTime()));
        emprunt.setDateRetour(new java.sql.Date(Date.from(dateRetour.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));

        e.setDisponible(false);
        daoExemplaire.update(e);
        daoEmprunt.create(emprunt);
    }

    public void rendreLivre(int idEmprunt) {
        Emprunt emprunt = getById(idEmprunt);
        emprunt.setDateRetour(new java.sql.Date(new Date().getTime()));
        daoEmprunt.update(emprunt);
        Exemplaire e = emprunt.getExemplaire();
        e.setDisponible(true);
        daoExemplaire.update(e);
    }

    public Emprunt getById(int idEmprunt) {
        return daoEmprunt.findEmpruntByID((long) idEmprunt);
    }

    public void deleteEmprunt(int idEmprunt) {
        Emprunt emprunt = getById(idEmprunt);
        Exemplaire e = emprunt.getExemplaire();
        e.setDisponible(true);
        daoExemplaire.update(e);
        daoEmprunt.delete(emprunt);
    }
}
