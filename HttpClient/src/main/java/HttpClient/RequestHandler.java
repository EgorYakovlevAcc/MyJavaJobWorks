package HttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class RequestHandler {
    private String method;
    private String urlStr;

    RequestHandler (String method, String urlStr) {
        this.method = method;
        this.urlStr = urlStr;
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

    public void setURLConnection(String urlStr) throws MalformedURLException {
        URL url = new URL(urlStr);
    }

    public void showValuesForKeys (HttpURLConnection huc) throws IOException {
        Map <String, List<String>> fieldMap = huc.getHeaderFields();
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(method);
//        System.out.println("Request method: " + httpURLConnection.getRequestMethod());
//        System.out.println("Answer: " + httpURLConnection.getResponseMessage());
//        showValuesForKeys(httpURLConnection);
//        Set<String> fieldSet = fieldMap.keySet();
//        for (String k: fieldSet) {
//            System.out.println("Key: " + k + "| value: " + fieldMap.get(k));
//        }
    }
}