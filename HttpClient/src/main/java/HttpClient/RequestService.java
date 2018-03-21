package HttpClient;

import java.net.ProtocolException;

public class RequestService extends RequestHandler{

    RequestService(String method, String urlStr) {
        super(method, urlStr);
    }

    @Override
    public void getMethod() {
        System.out.println("Use HTTP method GET:");
        showValuesForKeys();
    }
    @Override
    public void headMethod() {
        System.out.println("Use HTTP method HEAD:");
    }
    @Override
    public void optionsMethod() {
        System.out.println("Use HTTP method OPTIONS:");
    }
    @Override
    public void traceMethod() {
        System.out.println("Use HTTP method TRACE:");
    }
    @Override
    public void patchMethod() {
        System.out.println("Use HTTP method PATCH:");
        showValuesForKeys();
    }
    @Override
    public void putMethod() {
        System.out.println("Use HTTP method PUT:");
    }
    @Override
    public void postMethod() {
        System.out.println("Use HTTP method POST:");
    }
    @Override
    public void connectMethod() {
        System.out.println("Use HTTP method CONNECT:");
        showValuesForKeys();
    }
    @Override
    public void deleteMethod() {
        System.out.println("Use HTTP method DELETE:");
    }
}
