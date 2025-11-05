package de.fhswf.praktikum3;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.Crypt;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String user = req.getParameter("username");
        String password = req.getParameter("password");

        if (user == null || password == null || user.isEmpty() || password.isEmpty()) {
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Login Fehler</title></head><body>");
            out.println("<h3>Fehler: Benutzername oder Passwort fehlt!</h3>");
            out.println("</body></html>");
            return;
        }

        //Alte Session wird gekillt
        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        ServletContext context = getServletContext();
        String validUser = context.getInitParameter("validUser");
        String validPassword = context.getInitParameter("hashPassword");
        String salt = context.getInitParameter("salt");

        //TODO auf null prüfen

        // Daten hashen
        String hashedInput = Crypt.crypt(password, validPassword);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>LoginServlet</title></head><body>");
        out.println("<h2>Login Ergebnis</h2>");

        // Verifizierung
        if(user != null && user.equals(validUser) && hashedInput.equals(validPassword)) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedIn", true);

            out.println("<h3>Login erfolgreich!</h3>");
            out.println("<p>SHA-256 gehashte Eingabe: " + hashedInput + "</p>");
            out.println("<p>SHA-256 gespeicherte: " + validPassword + "</p>");
        } else {
         out.println("<h3>Login fehlgeschlagen!</h3>");
        }

        out.println("</body></html>");




    }
}
