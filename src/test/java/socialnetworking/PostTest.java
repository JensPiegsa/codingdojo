package socialnetworking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test @DisplayName("a posting has a username, message and time.")
    void aPostingHasAUsernameMessageAndTime() {
        String username = "a";
        String message = "m";
        Post post = new Post(username, message);
    }
}