import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;

public class TagRequestService extends SimpleTagSupport {
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        out.println("Hello Custom Tag!");
    }
}
