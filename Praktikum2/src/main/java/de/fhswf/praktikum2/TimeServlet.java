package de.fhswf.praktikum2;

import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        res.setHeader("Refresh", "1");

        PrintWriter out = res.getWriter();
        Date now = new Date();
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Time</title></head><body><h2>Aktuelle Uhrzeit</h2>");
        out.println("<p>" + now.toString() + "</p>");
        out.println("</body></html>");
    }
}