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
        methodStr = scn.next();
        url = scn.next();
        RequestHandler rh = new RequestHandler(methodStr, "http://" + url);
    }
}
