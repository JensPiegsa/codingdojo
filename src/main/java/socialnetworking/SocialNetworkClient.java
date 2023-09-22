package socialnetworking;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class SocialNetworkClient {
    private final String serverBaseUrl;

    public SocialNetworkClient(final String serverBaseUrl) {

        this.serverBaseUrl = serverBaseUrl;
    }

    public static void main(final String[] args) {
        
        final SocialNetworkClient client = new SocialNetworkClient("http://localhost:8087");
        
        if (args.length >= 2) {

        } else {
            final String username = args[0];
            final String timeline = client.reading(username);
            System.out.println(timeline);
        } 
    }

    public void post(final String username, final String message) {

        final HttpClient httpClient = HttpClient.newHttpClient();
        Map<String, List<Post>> postings;
        
        //throw new IllegalStateException("not yet implemented");

        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(username + " -> " + message);
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(bodyPublisher).uri(new URI(serverBaseUrl + "/sns"))
                    .header("Accept", "text/plain")
                    .build();
            final HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String reading(final String username) {
        
        final HttpClient httpClient = HttpClient.newHttpClient();
        try {
            final HttpRequest request = HttpRequest.newBuilder()
                    .GET().uri(new URI(serverBaseUrl + "/sns"))
                    .header("Accept", "text/plain")
                    .build();

            final HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
            return response.body();

        } catch (final IOException e) {
            throw new RuntimeException(e);
        } catch (final InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
