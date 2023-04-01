package ma.fstm.ilisi2.libraryapp.controler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.fstm.ilisi2.libraryapp.model.bo.Account;
import ma.fstm.ilisi2.libraryapp.model.bo.Adherent;
import ma.fstm.ilisi2.libraryapp.model.bo.Emprunt;
import ma.fstm.ilisi2.libraryapp.model.bo.Livre;
import ma.fstm.ilisi2.libraryapp.model.services.ServiceAdherent;
import ma.fstm.ilisi2.libraryapp.model.services.ServiceEmprunt;
import ma.fstm.ilisi2.libraryapp.model.services.ServiceLivre;

import java.io.IOException;
import java.util.List;

public class Bibliotheque extends HttpServlet {
    private ServiceLivre serviceLivre;
    private ServiceAdherent serviceAdherent;
    private ServiceEmprunt serviceEmprunt;

    @Override
    public void init() throws ServletException {
        super.init();
        serviceLivre = new ServiceLivre();
        serviceAdherent = new ServiceAdherent();
        serviceEmprunt = new ServiceEmprunt();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Check if the account is authenticated
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        System.out.println("user : "+account);
        if (account == null) {
            // If the account is not authenticated, redirect to the login page
            System.out.println("index page : "+req.getContextPath());

            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        String action;
        switch (req.getServletPath()){
            case "/Livre.do":
                action = req.getPathInfo();
                if (action == null) {
                    // If no action is specified, show the book list
                    List<Livre> books = (List<Livre>) serviceLivre.retrieveLivres();
                    req.setAttribute("books", books);
                    req.getRequestDispatcher("/WEB-INF/booklist.jsp").forward(req, resp);
                }
                break;
            case "/Adherent.do":
                action = req.getPathInfo();
                if (action == null) {
                    // If no action is specified, show the adherent list
                    List<Adherent> adherents = (List<Adherent>) serviceAdherent.getAllAdherents();
                    req.setAttribute("adherents", adherents);
                    req.getRequestDispatcher("/WEB-INF/adherentlist.jsp").forward(req, resp);
                }
                break;
            case "/Emprunt.do":
                action = req.getPathInfo();
                if (action == null) {
                    req.setAttribute("adherents", serviceAdherent.getAllAdherents());
                    req.setAttribute("books", serviceLivre.retrieveLivreDisponible());
                    req.setAttribute("copies", serviceEmprunt.getAllEmprunts());
                    for (Emprunt emprunt : serviceEmprunt.getAllEmprunts()) {
                        System.out.println(emprunt);
                    }
                    req.getRequestDispatcher("/WEB-INF/empruntlist.jsp").forward(req, resp);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Check if the account is authenticated
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            // If the account is not authenticated, redirect to the login page
            System.out.println("did it");
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }
        switch (req.getServletPath()){
            case "/Livre.do":
            switch (req.getParameter("action")){
                case "Ajouter":
                    serviceLivre.createLivre(req.getParameter("isbn"),req.getParameter("title"),
                            req.getParameter("author"),Integer.parseInt(req.getParameter("example")));
                    //req.getRequestDispatcher("/booklist.jsp").forward(req, resp);
                    break;
                case "Supprimer":
                    System.out.println("delete " + Integer.parseInt(req.getParameter("idLivre")));
                    serviceLivre.deleteLivreByID(Integer.parseInt(req.getParameter("idLivre")));
                    break;
                case "Modifier":
                    serviceLivre.updateLivre(Integer.parseInt(req.getParameter("idLivre")),
                            req.getParameter("author"),
                            req.getParameter("isbn"),
                            req.getParameter("title"),
                            Integer.parseInt(req.getParameter("example")));
                    break;
                default:
                    req.setAttribute("books", serviceLivre.retrieveLivres());
                    req.getRequestDispatcher("/WEB-INF/booklist.jsp").forward(req, resp);
            }
                resp.sendRedirect(req.getContextPath() + "/Livre.do");
                break;
            case "/Adherent.do":
                switch (req.getParameter("action")){
                    case "Ajouter":
                        serviceAdherent.createAdherent(req.getParameter("nomAdherent"),
                                req.getParameter("prenomAdherent"));
                        break;
                    case "Supprimer":
                        serviceAdherent.deleteAdherent(Integer.parseInt(req.getParameter("idAdherent")));
                        break;
                    case "Modifier":
                        serviceAdherent.updateAdherent(Integer.parseInt(req.getParameter("idAdherent")),
                                req.getParameter("nomAdherent"),
                                req.getParameter("prenomAdherent"));
                        break;
                    default:
                        req.setAttribute("adherents", serviceAdherent.getAllAdherents());
                        req.getRequestDispatcher("/WEB-INF/adherentlist.jsp").forward(req, resp);
                }
                resp.sendRedirect(req.getContextPath() + "/Adherent.do");
                break;

            case "/Emprunt.do":
                switch (req.getParameter("action")){
                    case "Emprunter":
                        serviceEmprunt.createEmprunt(Integer.parseInt(req.getParameter("empruntexemplaire")),
                                Integer.parseInt(req.getParameter("idAdherent")),
                                req.getParameter("dateRetour"));
                        break;
                    case "Retourner":
                        serviceEmprunt.rendreLivre(Integer.parseInt(req.getParameter("idemprunt")));
                        break;
                    case "Supprimer":
                        serviceEmprunt.deleteEmprunt(Integer.parseInt(req.getParameter("idemprunt")));
                        break;
                    default:
                        req.setAttribute("adherents", serviceAdherent.getAllAdherents());
                        req.setAttribute("books", serviceLivre.retrieveLivreDisponible());
                        req.setAttribute("copies", serviceEmprunt.getAllEmprunts());
                        for (Emprunt emprunt : serviceEmprunt.getAllEmprunts()) {
                            System.out.println(emprunt);
                        }
                        req.getRequestDispatcher("/WEB-INF/empruntlist.jsp").forward(req, resp);
                }
                resp.sendRedirect(req.getContextPath() + "/Emprunt.do");
        }
    }
}
