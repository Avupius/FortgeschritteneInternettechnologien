package de.fhswf.praktikum5_2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.Crypt;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @PersistenceUnit(unitName = "AccountPU")
    private EntityManagerFactory emf;

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

        EntityManager em = emf.createEntityManager();

        try {
            // Benutzer über Persistence Layer laden
            Account acc = em.find(Account.class, InputUser);

            out.println("<h2>Login Ergebnis</h2>");

            if (acc == null) {
                out.println("<h3>Login fehlgeschlagen: Benutzer existiert nicht!</h3>");
                out.println("</body></html>");
                return;
            }

            // Salt + gespeicherten Hash aus DB
            String storedHash = acc.getHashPassword();
            String storedSalt = acc.getSalt();

            // Hashing
            String hashedInput = Crypt.crypt(InputPassword, "$5$" + storedSalt);

            // Prüfung
            if (hashedInput.equals(storedHash)) {
                HttpSession session = req.getSession();
                session.setAttribute("loggedIn", true);

                out.println("<h3>Login erfolgreich!</h3>");
                out.println("<p>Benutzer: " + acc.getAccount() + "</p>");
            } else {
                out.println("<h3>Login fehlgeschlagen: Passwort falsch!</h3>");
            }



        } finally {
            em.close();
        }

        out.println("</body></html>");

    }
}
