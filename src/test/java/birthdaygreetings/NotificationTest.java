package birthdaygreetings;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NotificationTest {

    @Test
    @DisplayName("null arguments are not allowed on construction")
    void nullArgumentsAreNotAllowedOnConstruction() {
        assertThrows(NullPointerException.class,
                () -> new Notification(null, null, null));
    }

}