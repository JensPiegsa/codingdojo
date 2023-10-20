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

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.Response;

@ExtendWith(RestAssuredExtension.class)
class SocialNetworkServerTest {
    
    @Test @DisplayName("will accept posted message.")
    void willAcceptPostedMessage() {

        final int port = 8088;
        final SocialNetworkServer server = new SocialNetworkServer(port);
        server.start();

        given()
                .accept(ContentType.TEXT)
                .body("Hello World!")
        .when()
                .post("http://localhost:" + port + "/sns/posting/Bob")

        .then()
                .statusCode(Response.Status.ACCEPTED.getStatusCode());
    }

    @Test @DisplayName("will not accept posting.")
    void willNotAcceptPosting() {
        final int port = 8093;
        final SocialNetworkServer server = new SocialNetworkServer(port);
        server.start();

        given()
                .accept(ContentType.TEXT)
                .body("Hello World!")
        .when()
                .post("http://localhost:" + port + "/sns/posting/")

        .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
    }

    @Test @DisplayName("will return timeline on get request.")
    void willReturnTimelineOnGetRequest() {
        final int port = 8089;
        final SocialNetworkServer server = new SocialNetworkServer(port);
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
        final SocialNetworkServer server = new SocialNetworkServer(port);

        String result = server.calculateEndpoint(urlPartPath);

        then(result).isEqualTo(expected);
    }

    @ParameterizedTest @DisplayName("can handle null endpoint.")
    @NullSource()
    void canCalculateMethodEndpoint(String urlPartPath) {
        final int port = 8091;
        final SocialNetworkServer server = new SocialNetworkServer(port);

        String result = server.calculateEndpoint(urlPartPath);

        then(result).isEqualTo(null);
    }
}