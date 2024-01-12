package socialnetworking;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.*;

import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

@WireMockTest
class SocialNetworkClientTest {

    SocialNetworkClient client;
    
    @BeforeEach
    void setUp(final WireMockRuntimeInfo wiremockRuntimeInfo) {
        client = new SocialNetworkClient(wiremockRuntimeInfo.getHttpBaseUrl());
    }

    @Nested @DisplayName("reading method")
    class Reading{

        @Test @DisplayName("can read empty timeline from server.")
        void canReadEmptyTimelineFromServer() {

            final String endpoint = "reading";
            final String username = "bob";
            final String resultBodyContent = "";
            stubFor(get("/sns/" + endpoint + "/" + username).withHeader("Accept", containing("text/plain")).willReturn(ok(resultBodyContent)));

            String output = client.reading(username);

            verifyGetEndpointWithUser(endpoint, username);
            then(output).isEmpty();
        }
        @Test @DisplayName("can read single line from timeline from server.")
        void canReadSingleLineTimelineFromServer(final WireMockRuntimeInfo wiremockRuntimeInfo) {

            final String endpoint = "reading";
            final String username = "bob";
            final String resultBodyContent = "Alice - I love the weather today (5 minutes ago)";
            stubFor(get("/sns/" + endpoint + "/" + username).withHeader("Accept", containing("text/plain")).willReturn(ok(resultBodyContent)));

            String output = client.reading(username);

            verifyGetEndpointWithUser(endpoint, username);
            then(output).isEqualTo("Alice - I love the weather today (5 minutes ago)");
        }

    }

    @Nested @DisplayName("post method")
    class Post{

        @Test @DisplayName("can post message.")
        void canPostMessage() {

            client.post("Alice", "I love the weather today");

            verify(postRequestedFor(urlEqualTo("/sns/posting/Alice"))
                    .withHeader("Content-Type", containing("text/plain"))
                    .withRequestBody(equalTo("Alice -> I love the weather today")));
        }
    }

    @Nested @DisplayName("wall method")
    class Wall{

        @Test @DisplayName("can read empty wall from server.")
        void canReadEmptyWallFromServer() {

            final String endpoint = "wall";
            final String username = "bob";
            final String resultBodyContent = "";
            stubFor(get("/sns/" + endpoint + "/" + username).withHeader("Accept", containing("text/plain")).willReturn(ok(resultBodyContent)));

            String output = client.wall(username);

            verifyGetEndpointWithUser(endpoint, username);
            then(output).isEmpty();
        }

        @Test @DisplayName("can read simple wall from server.")
        void canReadSimpleWallFromServer() {
            final String endpoint = "wall";
            final String username = "bob";
            final String resultBodyContent =
                    "Bob - Damn! We lost! (2 minutes ago)\n" +
                    "Alice - I love the weather today (5 minutes ago)";
            stubFor(get("/sns/" + endpoint + "/" + username).withHeader("Accept", containing("text/plain")).willReturn(ok(resultBodyContent)));

            String output = client.wall(username);

            verifyGetEndpointWithUser(endpoint, username);
            then(output).isEqualTo(resultBodyContent);
        }
    }

    @Nested @DisplayName("follows method")
    class Follows{

        @Test @DisplayName("can set follower.")
        void canSetFollower() {

            final String endpoint = "follows";
            final String username = "bob";
            final String usernameToFollow = "charlie";
            final String resultBodyContent = "";
            stubFor(post("/sns/" + endpoint + "/" + username + "/" + usernameToFollow)
                    .withHeader("Accept", containing("text/plain"))
                    .willReturn(ok(resultBodyContent)));

            client.follows(username, usernameToFollow);

            // TODO get status code 202 if user exists, 4xx if no user found
            verify(postRequestedFor(urlEqualTo("/sns/" + endpoint + "/" + username)));
        }


    }

    @Nested @DisplayName("Malformed Arguments")
    class MalformedArguments {

        PrintStream originalOut;
        ByteArrayOutputStream outContent;
        @BeforeEach
        void setUp() {
            outContent = new ByteArrayOutputStream();
            originalOut = System.out;
            System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
        }

        @AfterEach
        void tearDown() {
            System.setOut(originalOut);
        }

        @Test @DisplayName("show usage information.")
        void showUsageInformation() {
            
            SocialNetworkClient.main(new String[]{});

            then(outContent.toString()).contains("usage");
        }


    }

    private static void verifyGetEndpointWithUser(String endpoint, String username) {
        verify(getRequestedFor(urlEqualTo("/sns/" + endpoint + "/" + username)).withHeader("Accept", containing("text/plain")));
    }
}