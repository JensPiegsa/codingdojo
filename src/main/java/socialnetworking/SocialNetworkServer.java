package socialnetworking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * @author Jens Piegsa
 */
public class SocialNetworkServer {

    private HttpServer httpServer;

    public SocialNetworkServer () {
    }

    public static void main(final String... args) {
        final SocialNetworkServer server = new SocialNetworkServer();
        server.start();
    }

    public void start() {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(8087), 0);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        httpServer.createContext("/sns", new MyHandler());
        httpServer.setExecutor(null); // creates a default executor
        httpServer.start();
    }
    
    public void stopInstantly() {
        httpServer.stop(0);
    }

    static class MyHandler implements HttpHandler {

        private static final int OK = 200;
        private static final int ACCEPTED = 202;

        @Override
        public void handle(final HttpExchange httpExchange) throws IOException {
            
            final InputStream requestBodyStream = httpExchange.getRequestBody();
            final String requestBody = toString(requestBodyStream);
            
            final String response;
            final int responseStatusCode;
            if (requestBody.contains("->")) {
                response = "";                
                responseStatusCode = ACCEPTED;
            } else {
                response = "Alice - I love the weather today (5 minutes ago)";
                responseStatusCode = OK;
            }

            httpExchange.sendResponseHeaders(responseStatusCode, response.length());
            final OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        }

        public static String toString(final InputStream input) throws IOException {
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
