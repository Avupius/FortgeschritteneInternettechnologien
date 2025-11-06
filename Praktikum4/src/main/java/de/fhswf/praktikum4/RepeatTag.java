package de.fhswf.praktikum4;

import jakarta.servlet.jsp.tagext.BodyTagSupport;

import java.io.PrintWriter;

public class RepeatTag extends BodyTagSupport {

    private int times;

    public void setTimes(int times ){
        this.times = times;
    }

}