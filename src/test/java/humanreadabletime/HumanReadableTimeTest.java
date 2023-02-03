package humanreadabletime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HumanReadableTimeTest {

    @Nested @DisplayName("acceptance test")
    class AcceptanceTest {

        @Test
        public void Tests() {
            assertEquals( "00:00:00", HumanReadableTime.makeReadable(0), "makeReadable(0)");
            assertEquals( "00:00:05", HumanReadableTime.makeReadable(5), "makeReadable(5)");
            assertEquals( "00:01:00", HumanReadableTime.makeReadable(60), "makeReadable(60)");
            assertEquals( "23:59:59", HumanReadableTime.makeReadable(86399), "makeReadable(86399)");
            assertEquals( "99:59:59", HumanReadableTime.makeReadable(359999), "makeReadable(359999)");
        }
    }
}