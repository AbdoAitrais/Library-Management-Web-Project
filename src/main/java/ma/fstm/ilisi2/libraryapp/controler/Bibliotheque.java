package ma.fstm.ilisi2.libraryapp.controler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstm.ilisi2.libraryapp.model.bo.Livre;
import ma.fstm.ilisi2.libraryapp.model.dao.DAOLivre;

import java.io.IOException;

public class Bibliotheque extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()){
            case "/Livre.do":
            switch (req.getParameter("action")){
                case "Ajouter":
                    String isbn=req.getParameter("isbn");
                    String titre=req.getParameter("title");
                    String auteur=req.getParameter("author");
                    String nbrExemplaire=req.getParameter("example");
                    int nbr = 0;
                    if (!nbrExemplaire.isEmpty()){
                        nbr = Integer.parseInt(nbrExemplaire);
                    }

                    DAOLivre dao=new DAOLivre();
                    Livre L=new Livre();
                    L.setIsbn(isbn);
                    L.setTitre(titre);
                    L.setAuteur(auteur);
                    dao.create(L, nbr);
                    req.getRequestDispatcher("/booklist.jsp").forward(req, resp);
                    break;
                case "Supprimer":
                    break;
                case "Modifier":
                    break;
                case "Retrieve":
            }
                break;
            case "/Adherent.do":
                switch (req.getParameter("action")){
                    case "Ajouter":

                        break;
                    case "Supprimer":
                        break;
                    case "Modifier":
                        break;
                    case "Retrieve":
                }
                break;

            case "/Emprunt.do":
                switch (req.getParameter("action")){
                    case "Emprunter":

                        break;
                    case "Retourner":
                }
                break;

            case "/Session.do":

                break;
        }
    }
}
