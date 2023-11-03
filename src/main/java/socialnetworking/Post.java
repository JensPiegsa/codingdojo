package socialnetworking;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", Post.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("message='" + message + "'")
                .add("time=" + time)
                .toString();
    }
}
