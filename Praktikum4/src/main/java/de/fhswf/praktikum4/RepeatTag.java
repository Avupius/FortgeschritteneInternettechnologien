package de.fhswf.praktikum4;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.BodyTag;
import jakarta.servlet.jsp.tagext.BodyTagSupport;


import java.io.IOException;

public class RepeatTag extends BodyTagSupport {

    private int times = 0;
    private int count = 0;

    public void setTimes(String timesStr ){
        try{
            this.times = Integer.parseInt(timesStr);
        } catch (NumberFormatException e) {
            this.times = 0;
        }
    }

    @Override
    public int doStartTag() throws JspException {
        count = 0;
        if(times <= 0){
            return SKIP_BODY;
        }
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doAfterBody() throws JspException {
        count++; // Wir haben den Body einmal ausgeführt
        if (count < times) {
            try {
                // Body-Inhalt an die Ausgabe anhängen
                getBodyContent().writeOut(getPreviousOut());
            } catch (IOException e) {
                throw new JspException("Error in RepeatTag", e);
            }
            // Body erneut auswerten
            return EVAL_BODY_AGAIN;
        } else {
            // Fertig, Body nicht mehr wiederholen
            return SKIP_BODY;
        }
    }




}