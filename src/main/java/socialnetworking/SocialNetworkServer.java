package socialnetworking;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Jens Piegsa
 */
public class SocialNetworkServer {

    static final Logger logger = Logger.getLogger(SocialNetworkServer.class.getName());
    public static final int DEFAULT_PORT = 8087;
    public static final String contextPath = "/sns";
    private final int port;
    private final PostStore postStore;
    private HttpServer httpServer;

    public SocialNetworkServer () {
        this(DEFAULT_PORT);
    }

    public SocialNetworkServer(final int port) {
        this(port, new InMemoryPostStore());
    }
    
    public SocialNetworkServer(final int port, final PostStore postStore) {
        this.port = port;
        this.postStore = postStore;
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

    public String calculateEndpoint(final String urlPartPath) {
        if (urlPartPath == null) {
            return null;
        }
        final int index = urlPartPath.indexOf('\\');
        return urlPartPath.substring(0, index);
    }

    class MyHandler implements HttpHandler {

        private static final int OK = 200;
        private static final int ACCEPTED = 202;
        private static final int BAD_REQUEST = 400;

        @SuppressWarnings("ImplicitNumericConversion")
        @Override
        public void handle(final HttpExchange httpExchange) throws IOException {

            try {
                final InputStream requestBodyStream = httpExchange.getRequestBody();
                final String requestBody = toString(requestBodyStream);
                final URI requestURI = httpExchange.getRequestURI();
                final String urlPath = requestURI.getPath();
                final String urlPathPart = StringUtils.removeStart(urlPath, contextPath);

                final String response;
                final int responseStatusCode;

                final String requestMethod = httpExchange.getRequestMethod();
                switch (StringUtils.substringBetween(urlPathPart, "/")) {
                    case "posting": {
                        final String urlSegmentTail = StringUtils.removeStart(urlPathPart, "/posting");
                        logger.info("Posting!");
                        final String username = StringUtils.removeStart(urlSegmentTail, "/");
                        
                        logger.info("username: " + username);
                        if (StringUtils.isEmpty(username)) {
                            responseStatusCode = BAD_REQUEST;
                        } else {
                            responseStatusCode = ACCEPTED;
                            postStore.persist(new Post("", ""));
                        } 
                        response = "";
                        break;
                    }
                    case null:
                        logger.info("Null!");
                    default:
                        logger.info("Default!");
                        final boolean isPostMessage = requestBody.contains("->");
                        if (isPostMessage) {
                            response = "";
                            responseStatusCode = ACCEPTED;
                        } else {
                            final String username = StringUtils.removeStart(urlPathPart, "/");
                            if ("alice".equals(username.toLowerCase(Locale.ROOT))) {
                                response = "Alice - I love the weather today (5 minutes ago)";
                            } else {
                                response = "";
                            }
                            responseStatusCode = OK;
                        }
                }

                httpExchange.sendResponseHeaders(responseStatusCode, response.length());
                final OutputStream outputStream = httpExchange.getResponseBody();
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
                outputStream.close();
            } catch (final Throwable e) {
                e.printStackTrace();
            }
        }

        public static String toString(final InputStream input) throws IOException {
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
