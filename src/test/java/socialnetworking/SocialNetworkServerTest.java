package socialnetworking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SocialNetworkServerTest {

    @Test @DisplayName("can handle post.")
    void canHandlePost() {
        final SocialNetworkServer server = new SocialNetworkServer();
        server.start();
        
        server.post
    }
}