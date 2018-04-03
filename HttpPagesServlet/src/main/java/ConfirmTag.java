import org.xml.sax.SAXException;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ConfirmTag extends SimpleTagSupport {

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        try {
            out.println((new FileXmlHandler().parcerXml()));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}