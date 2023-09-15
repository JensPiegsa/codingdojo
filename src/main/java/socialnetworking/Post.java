package socialnetworking;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class Post {
    private final String username;
    private final String message;
    private final LocalDateTime time;

    public Post(String username, String message) {

        this.username = username;
        this.message = message;
        this.time = now();
    }
}
