package socialnetworking;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.StringJoiner;

import static java.time.LocalDateTime.now;

public class Post {
    private final String username;
    private final String message;
    private final ZonedDateTime time;

    @Deprecated
    public Post(String username, String message) {
        this.username = username;
        this.message = message;
        this.time = ZonedDateTime.now();
    }

    public Post(final String username, final String message, final ZonedDateTime time) {
        this.username = username;
        this.message = message;
        this.time = time;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Post post = (Post) o;
        return Objects.equals(username, post.username) && Objects.equals(message, post.message) && Objects.equals(time, post.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, message, time);
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Post.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("message='" + message + "'")
                .add("time=" + time)
                .toString();
    }
}
