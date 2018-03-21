package HttpClient;

import java.io.IOException;
import java.util.Scanner;

public class App
{
    static String url;
    Scanner scn;

    public static void main( String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);
        url = scn.nextLine();
        RequestHandler rh = new RequestHandler(url);
    }
}
