package main.java;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowTableDB extends SimpleTagSupport {
    ArrayList<Person> list;
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter writer = getJspContext().getOut();
        writer.print("Custom tag again works");
        try {
            list= new ArrayList<Person>();
            JDBCtest db = new JDBCtest();
            db.setConnect();
            list = db.getPersons();
            for (Person p: list) {
                writer.print(p.id + "<br>");
                writer.print(p.name + "<br>");
                writer.print(p.age + "<br>");
                writer.print("<br>");
             }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
