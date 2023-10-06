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

        Map<String, List<Post>> postings;
        
        //throw new IllegalStateException("not yet implemented");

        httpPost("posting", username, message);
    }

    public void follows(String username, String usernameToFollow) {
        final String message = username + "follows" + usernameToFollow;
        httpPost("follows", username, message);
    }

    public String reading(final String username) {
        return httpGet(username, "reading");
    }

    public String wall(String username) {
        return httpGet(username, "wall");
    }

    private void httpPost(String endpoint, String username, String message) {
        final HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(username + " -> " + message);
        HttpRequest.Builder builder = HttpRequest.newBuilder().POST(bodyPublisher);
        httpAction(endpoint, username, builder, "Content-Type");
    }

    private String httpGet(String username, String endpoint) {
        HttpRequest.Builder builder = HttpRequest.newBuilder().GET();
        return httpAction(endpoint, username, builder, "Accept");
    }

    private String httpAction(String endpoint, String username, HttpRequest.Builder builder, String headerName) {
        final HttpClient httpClient = HttpClient.newHttpClient();
        try {
            final HttpRequest request = builder.uri(new URI(serverBaseUrl + "/sns/" + endpoint + "/" + username))
                    .header(headerName, "text/plain")
                    .build();
            final HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString(StandardCharsets.UTF_8));
            return response.body();
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
