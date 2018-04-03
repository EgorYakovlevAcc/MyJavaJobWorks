package main.java;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import main.java.User;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileXmlHandler {
    List<main.java.User> list = new ArrayList<User>();

    public FileXmlHandler () {

    }

    public List<main.java.User> parcerXml() throws ParserConfigurationException, IOException, SAXException {

        File fileXml = new File("//home/egoryakovlev/IdeaProjects/MyJavaJobWorks/TagsExamples/web/List.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(fileXml);
        Element root = document.getDocumentElement();
        NodeList people = root.getChildNodes();
        Node person = people.item(i);
        Element name = (Element) root.getElementsByTagName("name").item(i);
        Element surname = (Element) root.getElementsByTagName("surname").item(i);
        Element age = (Element) root.getElementsByTagName("age").item(i);
        Element country = (Element) root.getElementsByTagName("country").item(1);
        list.add (new User(name.getTextContent(), surname.getTextContent(), age.getTextContent(), country.getTextContent()));
        return list;
    }
}
