package socialnetworking;

import static java.time.LocalDateTime.now;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.List;
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

    private static final int OK = 200;
    private static final int ACCEPTED = 202;
    private static final int BAD_REQUEST = 400;

    static final Logger logger = Logger.getLogger(SocialNetworkServer.class.getName());
    public static final int DEFAULT_PORT = 8087;
    public static final String contextPath = "/sns";
    private final int port;
    private final PostStore postStore;
    private final Clock clock;
    private HttpServer httpServer;
    private final TimelineRender timelineRenderer;


    public SocialNetworkServer () {
        this(DEFAULT_PORT);
    }

    public SocialNetworkServer(final int port) {
        this(port, new InMemoryPostStore(), Clock.systemDefaultZone());
    }
    
    public SocialNetworkServer(final int port, final PostStore postStore, final Clock clock) {
        this.port = port;
        this.postStore = postStore;
        this.clock = clock;
        timelineRenderer = new TimelineRender(clock);
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

        @SuppressWarnings("ImplicitNumericConversion")
        @Override
        public void handle(final HttpExchange httpExchange) {

            try {
                final InputStream requestBodyStream = httpExchange.getRequestBody();
                final String requestBody = toString(requestBodyStream);
                final URI requestURI = httpExchange.getRequestURI();
                final String urlPath = requestURI.getPath();
                final String urlPathPart = StringUtils.removeStart(urlPath, contextPath);

                final Response response;

                final String requestMethod = httpExchange.getRequestMethod();
                switch (StringUtils.substringBetween(urlPathPart, "/")) {
                    case "posting":
                        response = postEndpoint(requestMethod, urlPathPart, requestBody);
                        break;
                    case null:
                        response = baseEndpoint(requestMethod, requestBody, urlPathPart);
                        break;
                    default:
                        response = new Response("", BAD_REQUEST);
                }

                httpExchange.sendResponseHeaders(response.statusCode(), response.body().length());
                final OutputStream outputStream = httpExchange.getResponseBody();
                outputStream.write(response.body().getBytes(StandardCharsets.UTF_8));
                outputStream.close();
            } catch (final Throwable e) {
                e.printStackTrace();
            }
        }

        public static String toString(final InputStream input) throws IOException {
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private Response baseEndpoint(final String requestMethod, final String requestBody, final String urlPathPart) {

        logger.info("requestMethod: " + requestMethod + " urlPathPart: " + urlPathPart + " requestBody: " + requestBody);

        if ("GET".equals(requestMethod)) {
            final String responseBody;
            final String username = StringUtils.removeStart(urlPathPart, "/");
            if ("alice".equals(username.toLowerCase(Locale.ROOT))) {
                final List<Post> timeline = postStore.readTimeline("alice");
                final String prettyTimeline = timelineRenderer.render(timeline);
                responseBody = prettyTimeline;
            } else {
                responseBody = "";
            }
            return new Response(responseBody, OK);
        } else {
            return new Response(requestMethod + " NOT SUPPORTED", BAD_REQUEST);
        } 
    }

    private Response postEndpoint(final String requestMethod, final String urlPathPart, final String requestBody) {
        logger.info("requestMethod: " + requestMethod + " urlPathPart: " + urlPathPart + " requestBody: " + requestBody);

        if ("POST".equals(requestMethod)) {
            final String urlSegmentTail = StringUtils.removeStart(urlPathPart, "/posting");
            logger.info("Posting!");
            final String username = StringUtils.removeStart(urlSegmentTail, "/");

            logger.info("username: " + username);
            final int responseStatusCode;
            if (StringUtils.isEmpty(username)) {
                responseStatusCode = BAD_REQUEST;
            } else {
                responseStatusCode = ACCEPTED;
                final ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(clock.instant(), clock.getZone());
                postStore.persist(new Post(username, requestBody, zonedDateTime));
            }
            return new Response("", responseStatusCode);
        } else {
            return new Response(requestMethod + " NOT SUPPORTED", BAD_REQUEST);
        } 
    }
}
