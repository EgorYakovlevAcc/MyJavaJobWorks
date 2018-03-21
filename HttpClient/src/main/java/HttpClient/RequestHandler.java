package HttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RequestHandler {

    RequestHandler (String urlStr) throws IOException {

        URL url = new URL(urlStr);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        System.out.println("Request method: " + httpURLConnection.getRequestMethod());
        System.out.println("Answer: " + httpURLConnection.getResponseMessage()); }

    private void showValueForKeys () {
        Map <String, List<String>> fieldMap = httpURLConnection.getHeaderFields();
        Set<String> fieldSet = fieldMap.keySet();
        for (String k: fieldSet) {
            System.out.println("Key: " + k + "| value: " + fieldMap.get(k));
        }
    }
}
