package socialnetworking;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class SocialNetworkClient {
    private final String serverBaseUrl;

    public SocialNetworkClient(final String serverBaseUrl) {

        this.serverBaseUrl = serverBaseUrl;
    }

    public static void main(String[] args) {

        System.out.println("Alice - I love the weather today (5 minutes ago)");
    }

    public static void post(String username, String message) {
        Map<String, List<Post>> postings;
    }

    public void reading(String username) {
        final HttpClient httpClient = HttpClient.newHttpClient();
        final HttpResponse.BodyHandler<?> handler = responseInfo -> { return null; };
        try {
            httpClient.send(HttpRequest.newBuilder().GET().uri(new URI(serverBaseUrl + "/sns")).build(), handler);
        } catch (IOException e) {
            
        } catch (InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
