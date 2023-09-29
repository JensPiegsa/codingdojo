package socialnetworking;

import com.github.jenspiegsa.restassuredextension.RestAssuredExtension;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
        .when()
                .post("http://localhost:" + port + "/sns")
        .then()
                .statusCode(Response.Status.ACCEPTED.getStatusCode());
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
}