package de.fhswf.praktikum6;


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
     */
    @Override
    public void init() {
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
        String inputUser = req.getParameter("username");
        String inputPassword = req.getParameter("password");
        String inputMode = req.getParameter("mode");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><body>");

        if (inputUser == null || inputPassword == null || inputMode == null|| inputUser.isEmpty() || inputPassword.isEmpty() || inputMode.isEmpty()) {
            out.println("<html><head><title>Login Fehler</title></head><body>");
            out.println("<h3>Fehler: Benutzername oder Passwort oder Weiterleitung fehlt!</h3>");
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
        boolean loginSuccess = accountManager.checkPassword(inputUser, inputPassword);

        if (loginSuccess) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", true);
            session.setAttribute("username", inputUser);

            if ("forward".equals(inputMode)) {
                resp.sendRedirect(req.getContextPath() + "/welcome.jsp");
            } else if ("redirect".equals(inputMode)) {
                getServletContext().getRequestDispatcher("/welcome.jsp").forward(req, resp);

            }


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
