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
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import javax.ws.rs.core.Response;

@ExtendWith(RestAssuredExtension.class)
@ExtendWith(MockitoExtension.class)
class SocialNetworkServerTest {

    @Mock PostStore postStore;

    @Test @DisplayName("will accept posted message.")
    void willAcceptPostedMessage() {
        final Instant instant = LocalDateTime.parse("2023-11-03T12:05:48.091942100").toInstant(ZoneOffset.UTC);
        Clock clock = Clock.fixed(instant, ZoneId.of("UTC"));

        final int port = 8088;

        final SocialNetworkServer server = constructServer(port, clock);
        server.start();

        given()
                .accept(ContentType.TEXT)
                .body("Hello World!")
        .when()
                .post("http://localhost:" + port + "/sns/posting/Bob")

        .then()
                .statusCode(Response.Status.ACCEPTED.getStatusCode());

        verify(postStore).persist(eq(new Post("Bob", "Hello World!", LocalDateTime.parse("2023-11-03T12:05:48.091942100"))));
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

        final LocalDateTime localDateTime = LocalDateTime.parse("2023-11-03T12:00:00.000000000");
        final Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        Clock clock = Clock.fixed(instant, ZoneId.of("UTC"));
        final Post post = new Post("alice", "I love the weather today", localDateTime);
        given(postStore.readTimeline("alice")).willReturn(List.of(post));

        given()
                .accept(ContentType.TEXT)
        .when()
                .get("http://localhost:" + port + "/sns/alice")
        .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(equalTo("I love the weather today (7 minutes ago)"));
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
        final Instant instant = LocalDateTime.parse("2023-11-03T12:00:00.000000000").toInstant(ZoneOffset.UTC);
        Clock clock = Clock.fixed(instant, ZoneId.of("UTC"));
        return new SocialNetworkServer(port, postStore, clock);
    }

    private SocialNetworkServer constructServer(int port, Clock clock) {
        return new SocialNetworkServer(port, postStore, clock);
    }
}