package de.fhswf.praktikum2;

import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RequestInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>RequestInfo</title></head>");
        out.println("<body><h2>Header-Informationen</h2><ul>");
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            out.println("<li>" + name + " = " + req.getHeader(name) + "</li>");
        }
        out.println("</ul>");

        out.println("<h2>Formulardaten</h2><ul>");
        Enumeration<String> paramNames = req.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            String[] values = req.getParameterValues(name);
            out.println("<li>" + name + " = " + String.join(", ", values) + "</li>");
        }
        out.println("</ul>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>RequestInfo</title></head><body>");
        out.println("<h2>Auswertung der Checkboxen (POST)</h2>");

        boolean fehler = false;

        String[] gruppeA = req.getParameterValues("gruppeA");
        String[] gruppeB = req.getParameterValues("gruppeB");

//        if (gruppeA != null && gruppeA.length > 1) {
//            out.println("<p style='color:red;'>Fehler: In Gruppe A wurden mehrere Checkboxen gewählt!</p>");
//            fehler = true;
//        }
//        if (gruppeB != null && gruppeB.length > 1) {
//            out.println("<p style='color:red;'>Fehler: In Gruppe B wurden mehrere Checkboxen gewählt!</p>");
//            fehler = true;
//        }

        if (!fehler) {
            out.println("<h3>Ausgewählte Werte:</h3><ul>");
            if (gruppeA != null) {
                out.println("<li>Gruppe A: " + String.join(", ", gruppeA) + "</li>");
            } else {
                out.println("<li>Gruppe A: keine Auswahl</li>");
            }
            if (gruppeB != null) {
                out.println("<li>Gruppe B: " + String.join(", ", gruppeB) + "</li>");
            } else {
                out.println("<li>Gruppe B: keine Auswahl</li>");
            }
            out.println("</ul>");
        }

        out.println("</body></html>");
    }
}