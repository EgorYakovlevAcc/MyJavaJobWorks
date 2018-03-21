package HttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class RequestHandler {
    private String urlStr;
    private HttpURLConnection huc;
    private String method;

    RequestHandler (String method, String urlStr) {
        this.urlStr = urlStr;
        this.method = method;
    }

    public void postMethod() {}
    public void getMethod() {}
    public void headMethod() {}
    public void optionsMethod() {}
    public void putMethod() {}
    public void patchMethod() {}
    public void deleteMethod() {}
    public void traceMethod() {}
    public void connectMethod() {}


    public void setURLConnection() throws IOException {
        URL url = new URL(urlStr);
        huc = (HttpURLConnection) url.openConnection();
    }

    public void useMethod () throws IOException {
        huc.setRequestMethod(method);
        if ("GET".equals(method)) {
            getMethod();
        } else if ("POST".equals(method)) {
            postMethod();
        } else if ("OPTIONS".equals(method)) {
            optionsMethod();
        } else if ("HEAD".equals(method)) {
            headMethod();
        } else if ("PUT".equals(method)) {
            putMethod();
        } else if ("PATCH".equals(method)) {
            patchMethod();
        } else if ("TRACE".equals(method)) {
            traceMethod();
        } else if ("CONNECT".equals(method)) {
            connectMethod();
        } else if ("DELETE".equals(method)) {
            deleteMethod();
        }
    }

    public void showValuesForKeys () {
        Map <String, List<String>> fieldMap = huc.getHeaderFields();
        Set<String> fieldSet = fieldMap.keySet();
        for (String k: fieldSet) {
            System.out.println("Key: " + k + "| value: " + fieldMap.get(k));
        }
    }
}