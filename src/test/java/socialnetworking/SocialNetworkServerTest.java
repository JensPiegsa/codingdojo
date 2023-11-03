package socialnetworking;

import com.github.jenspiegsa.restassuredextension.RestAssuredExtension;
import io.restassured.http.ContentType;

import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import javax.ws.rs.core.Response;

@ExtendWith(RestAssuredExtension.class)
@ExtendWith(MockitoExtension.class)
class SocialNetworkServerTest {
    @Mock
    PostStore postStore;

    @Test @DisplayName("will accept posted message with username.")
    void willAcceptPostedMessage() {

        final int port = 8088;
        final SocialNetworkServer server = constructServer(port);
        server.start();

        given()
                .accept(ContentType.TEXT)
                .body("Hello World!")
        .when()
                .post("http://localhost:" + port + "/sns/posting/Bob")

        .then()
                .statusCode(Response.Status.ACCEPTED.getStatusCode());

        verify(postStore).persist(eq(new Post("Bob", "Hello World!")));
    }

    @Test @DisplayName("will not accept posted message without username.")
    void willNotAcceptPosting() {
        final int port = 8093;
        final SocialNetworkServer server = constructServer(port);
        server.start();

        given()
                .accept(ContentType.TEXT)
                .body("Hello World!")
        .when()
                .post("http://localhost:" + port + "/sns/posting/") // missing /user

        .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
    }

    @Test @DisplayName("will return timeline on get request.")
    void willReturnTimelineOnGetRequest() {
        final int port = 8089;
        final SocialNetworkServer server = constructServer(port);
        server.start();

        given()
                .accept(ContentType.TEXT)
        .when()
                .get("http://localhost:" + port + "/sns/alice")
        .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(equalTo("Alice - I love the weather today (5 minutes ago)"));
    }

    @ParameterizedTest @DisplayName("can calculate method endpoint.")
    @CsvSource({"follows\\bla, follows",
                "wall\\bla, wall",
                "reading\\bla, reading",
                "posting\\bla, posting"})
    void canCalculateMethodEndpoint(String urlPartPath, String expected) {
        final int port = 8090;
        final SocialNetworkServer server = constructServer(port);

        String result = server.calculateEndpoint(urlPartPath);

        then(result).isEqualTo(expected);
    }

    @ParameterizedTest @DisplayName("can handle null endpoint.")
    @NullSource()
    void canCalculateMethodEndpoint(String urlPartPath) {
        final int port = 8091;
        final SocialNetworkServer server = constructServer(port);

        String result = server.calculateEndpoint(urlPartPath);

        then(result).isEqualTo(null);
    }

    private SocialNetworkServer constructServer(int port) {
        return new SocialNetworkServer(port, postStore);
    }
}