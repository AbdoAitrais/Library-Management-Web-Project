package ma.fstm.ilisi2.libraryapp.controler;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.fstm.ilisi2.libraryapp.model.bo.Account;
import ma.fstm.ilisi2.libraryapp.model.dao.DAOAccount;

import java.io.IOException;
import java.io.PrintWriter;


public class ServletSession extends HttpServlet {
    DAOAccount daoUser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoUser = new DAOAccount();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String action = req.getPathInfo();
        if (action.equals("/logout") && (session != null)){
            session.invalidate();
            //System.out.println("session : "+session.getAttribute("account"));
        }
        System.out.println("login endpoint : "+req.getContextPath());
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getPathInfo();
        System.out.println(action);

        switch (action){
            case "/login":{
                if (session.getAttribute("account") != null){
                    resp.sendRedirect(req.getContextPath() + "/biblio");
                }

                String username = req.getParameter("username");
                String password = req.getParameter("password");
                Account account = new Account();
                account.setUsername("abdo");
                account.setPassword("rais");

                if (username.equals("abdo") && password.equals("rais")){
                    session.setAttribute("account", account);
                    resp.sendRedirect(req.getContextPath() + "/Livre.do");
                } else{
                    resp.setContentType( "text/html" );
                    PrintWriter out = resp.getWriter();

                    out.println( "<html><body>" );
                    out.println("<p>Login ou mot de passe incorrect</p>");
                    out.println("<a href='"+ req.getContextPath() +"/'>back</a>");
                    out.println( "</body></html>" );

                }
                break;
            }
            case "/logout":{
                if (session != null) {
                    session.invalidate();
                }
                resp.sendRedirect(req.getContextPath() + "/login");
                break;
            }
        }


//        daoUser.create(acc);
//        System.out.println(username);
//        System.out.println(password);
//        Account account = daoUser.find(username,password);
//
//        if (account != null){
//            session.setAttribute("account", account);
//            req.getRequestDispatcher("/booklist.jsp").forward(req, resp);
//        }
    }
}
