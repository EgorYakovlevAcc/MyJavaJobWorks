import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class FileXmlHandler {
    public FileXmlHandler () {

    }

    public String parcerXml() throws ParserConfigurationException, IOException, SAXException {
        File fileXml = new File("/home/egoryakovlev/IdeaProjects/MyJavaJobWorks/TagsExamples/web/List.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fileXml);
        Element root = document.getDocumentElement();
        // для простоты сразу берем message
        Element message = (Element) root.getElementsByTagName("person").item(0);
        String textContent = message.getTextContent(); // тоже для упрощения
        return textContent;
    }

}
