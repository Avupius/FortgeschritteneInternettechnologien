package de.fhswf.praktikum3;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


public class LoginServlet1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Login</title></head><body>");

        String user = req.getParameter("username");
        String password = req.getParameter("password");

        if (user == null || password == null || user.isEmpty() || password.isEmpty()) {
            out.println("<h3>Fehler: Benutzername oder Passwort fehlt!</h3>");

        } else {

            ServletContext context = getServletContext();
            String validUser = context.getInitParameter("validUser");
            String validPass = context.getInitParameter("validPassword");

            if (user.equals(validUser) && password.equals(validPass)) {
                out.println("<h2>Login erfolgreich!</h2>");
            } else {
                out.println("<h2>Login fehlgeschlagen!</h2>");
            }
        }
        out.println("</body></html>");
    }
}
