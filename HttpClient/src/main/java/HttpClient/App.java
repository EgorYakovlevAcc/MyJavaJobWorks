package HttpClient;

import java.io.IOException;
import java.util.Scanner;

public class App
{
    static String url;
    Scanner scn;
    static String methodStr;

    public static void main( String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);
        while((methodStr == null) && (url == null)) {
            methodStr = scn.next();
            url = scn.next();
        }
        RequestHandler rh = new RequestService(methodStr,"http://" + url);
        rh.setURLConnection();
        rh.useMethod();
    }
}
