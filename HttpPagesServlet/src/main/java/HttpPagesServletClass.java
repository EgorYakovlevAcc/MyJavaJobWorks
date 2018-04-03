import org.xml.sax.SAXException;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

public class HttpPagesServletClass extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            new FileXmlHandler().parcerXml();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("test");
        requestDispatcher.forward(req, resp);

//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter pw = resp.getWriter();
//        String lastname = req.getParameter("lastname");
//        String name = req.getParameter("name");
//        getServletContext().getRequestDispatcher("configGettingRequest.jsp").forward(req, resp);
//        pw.write(lastname + name + " your request is getting by our server and even started to handle");
//        String pageid = reqAnalyst(req.getParameter("pageid"));
//        if (pageid == null) {
//            return;
//        }
//        readFromFileAndShow(pageid, pw);
    }

    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    private void readFromFileAndShow(String pageName, PrintWriter printWriter) throws IOException {
        String line;
        if (pageName != null) {
            File f = new File(pageName);
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            while ((line = bfr.readLine()) != null) {
                printWriter.println(line);
            }
        } else {
            printWriter.println("Change your entered paramaters!");
        }
    }

    private String reqAnalyst(String pageId) throws IOException {
        switch (pageId) {
            case "1": {
                return "/home/egoryakovlev/IdeaProjects/HttpPagesServlet/src/main/resources/page1.html";
            }
            case "2": {
                return "/home/egoryakovlev/IdeaProjects/HttpPagesServlet/src/main/resources/page2.html";
            }
            case "3": {
                return "/home/egoryakovlev/IdeaProjects/HttpPagesServlet/src/main/resources/page3.html";
            }
            default:{
                return null;
            }
        }
    }

}

