package socialnetworking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Jens Piegsa
 */
public class SocialNetworkServer {

    public static final int DEFAULT_PORT = 8087;
    public static final String contextPath = "/sns";
    private final int port;
    private HttpServer httpServer;

    public SocialNetworkServer () {
        this(DEFAULT_PORT);
    }

    public SocialNetworkServer(int port) {
        this.port = port;
    }

    public static void main(final String... args) {
        final SocialNetworkServer server = new SocialNetworkServer();
        server.start();
    }

    public void start() {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        httpServer.createContext(contextPath, new MyHandler());
        httpServer.setExecutor(null); // creates a default executor
        httpServer.start();
    }
    
    public void stopInstantly() {
        httpServer.stop(0);
    }

    static class MyHandler implements HttpHandler {

        private static final int OK = 200;
        private static final int ACCEPTED = 202;

        @SuppressWarnings("ImplicitNumericConversion")
        @Override
        public void handle(final HttpExchange httpExchange) throws IOException {

            try {
                final InputStream requestBodyStream = httpExchange.getRequestBody();
                final String requestBody = toString(requestBodyStream);
                final URI requestURI = httpExchange.getRequestURI();
                final String urlPath = requestURI.getPath();
                final String urlPathPart = StringUtils.removeStart(urlPath, contextPath);

                String response;
                final int responseStatusCode;

                String requestMethod = httpExchange.getRequestMethod();
                boolean isPostMessage = requestBody.contains("->");
                if (isPostMessage) {
                    response = "";                
                    responseStatusCode = ACCEPTED;
                } else {
                    String username = StringUtils.removeStart(urlPathPart, "/");
                    if ("alice".equals(username.toLowerCase(Locale.ROOT))) {
    
                        response = "Alice - I love the weather today (5 minutes ago)";
                    } else {
                        
                        response = "";
                    } 
                    responseStatusCode = OK;
                }

                httpExchange.sendResponseHeaders(responseStatusCode, response.length());
                final OutputStream outputStream = httpExchange.getResponseBody();
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                outputStream.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        public static String toString(final InputStream input) throws IOException {
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
