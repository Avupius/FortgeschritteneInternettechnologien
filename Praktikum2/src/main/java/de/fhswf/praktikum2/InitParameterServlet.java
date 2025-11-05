package de.fhswf.praktikum2;

import jakarta.servlet.GenericServlet;

import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(
        initParams = {
                @WebInitParam(name = "Name", value = "Kutko"),
                @WebInitParam(name = "Rolle", value = "Student")
        })


public class InitParameterServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Initialisierungsparameter</title></head>");
        out.println("<body><h2>Initialisierungsparameter</h2><ul>");
        Enumeration<String> params = getInitParameterNames();
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            String value = getInitParameter(name);
            out.println("<li>" + name + " = " + value + "</li>");
        }
        out.println("</ul></body></html>");
    }
}