package de.fhswf.praktikum6;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AccountManager accountManager;

    /**
     * Initialisierung des Servlets
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String puName = context.getInitParameter("persistenceUnitName");
        if (puName == null) {
            puName = "AccountPU";
        }

        accountManager = new AccountManager(puName);
    }

    /**
     * Verarbeitung von Login-Anfragen
     * @param req Request
     * @param resp Response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String InputUser = req.getParameter("username");
        String InputPassword = req.getParameter("password");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><body>");

        if (InputUser == null || InputPassword == null || InputUser.isEmpty() || InputPassword.isEmpty()) {
            out.println("<html><head><title>Login Fehler</title></head><body>");
            out.println("<h3>Fehler: Benutzername oder Passwort fehlt!</h3>");
            return;
        }

        //Alte Session wird gekillt
        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }


        /**
         * Passwortprüfung
         */
        boolean loginSuccess = accountManager.checkPassword(InputUser, InputPassword);
        if (loginSuccess) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", true);

            out.println("<h3>Login erfolgreich!</h3>");
            out.println("<p>Benutzer: " + InputUser + "</p>");
        } else {
            out.println("<h3>Login fehlgeschlagen: Passwort falsch!</h3>");
        }

        out.println("</body></html>");

    }

    @Override
    public void destroy() {
        if (accountManager != null) {
            accountManager.kill();
        }
    }
}
