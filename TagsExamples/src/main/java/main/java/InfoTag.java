package main.java;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;

public class InfoTag extends SimpleTagSupport {
    public void doTag() {
        try {
            JspWriter out = getJspContext().getOut();
            out.print("This string printed from custom tag");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}