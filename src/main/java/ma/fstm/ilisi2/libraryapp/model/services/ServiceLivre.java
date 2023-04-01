package ma.fstm.ilisi2.libraryapp.model.services;


import ma.fstm.ilisi2.libraryapp.model.bo.Exemplaire;
import ma.fstm.ilisi2.libraryapp.model.bo.Livre;
import ma.fstm.ilisi2.libraryapp.model.dao.DAOExemplaire;
import ma.fstm.ilisi2.libraryapp.model.dao.DAOLivre;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class ServiceLivre {
    DAOLivre daoLivre;
    DAOExemplaire daoExemplaire;
    public ServiceLivre(){
        daoLivre = new DAOLivre();
        daoExemplaire = new DAOExemplaire();
    }
    public void createLivre(String isbn, String titre, String auteur, int nbrExemplaire){
        Livre L=new Livre();
        L.setIsbn(isbn);
        L.setTitre(titre);
        L.setAuteur(auteur);
        daoLivre.create(L, nbrExemplaire);
    }
    public Livre getLivreById(int idLivre) {
        return daoLivre.findById(idLivre);
    }
    public Collection<Livre> retrieveLivres(){
        return daoLivre.retrieve();
    }

    public void updateLivre(int id,String auteur, String isbn, String titre, int nbExemplaires) {
        if (nbExemplaires < 0)
            throw new IllegalArgumentException("Le nombre d'exemplaires doit être supérieur à 0");
        Livre livre = getLivreById(id);
        livre.setAuteur(auteur);
        livre.setIsbn(isbn);
        livre.setTitre(titre);
        daoLivre.update(livre,nbExemplaires);

    }
    public void deleteLivreByID(int id){
        Livre l = daoLivre.findById(id);
        System.out.println("livre "+l.getIsbn());
        if (l != null) {
            daoLivre.delete(l);
        } else {
            throw new IllegalArgumentException("Le livre avec l'ID " + id + " n'existe pas!");
        }
    }
    public Exemplaire retrieveExemplaireDispo(int idLivre) {
        Livre livre = getLivreById(idLivre);
        return ( daoLivre.getAvailableExamplaires(livre).isEmpty() ? null : (Exemplaire) ((List<?>) daoLivre.getAvailableExamplaires(livre)).get(0));
    }
    public List<Livre> retrieveLivreDisponible() {
        return (List<Livre>) daoLivre.getAvailableLivres();
    }
}