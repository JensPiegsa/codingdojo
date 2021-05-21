package birthdaygreetings;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Notification {

    private final Person recipient;
    private final String subject;
    private final String message;

    public Notification(Person recipient, String subject, String message) {
        this.recipient = requireNonNull(recipient);
        this.subject = requireNonNull(subject);
        this.message = requireNonNull(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return recipient.equals(that.recipient) && subject.equals(that.subject) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipient, subject, message);
    }
}
