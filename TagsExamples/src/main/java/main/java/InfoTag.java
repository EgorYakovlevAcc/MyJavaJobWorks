package main.java;

import org.xml.sax.SAXException;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class InfoTag extends SimpleTagSupport {
    List<main.java.User> l;

    public void doTag() {
        try {
            JspWriter out = getJspContext().getOut();
            out.print("This string printed from custom tag");
            FileXmlHandler fileXmlHandler = new FileXmlHandler();
            try {
                l = fileXmlHandler.parcerXml();
                for (User u: l) {
                    out.println("Name: " + u.getName());
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}