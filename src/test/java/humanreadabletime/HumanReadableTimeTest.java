package humanreadabletime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * <a href="https://de.wikipedia.org/wiki/Sexagesimalsystem">Sexagesimalsystem</a>
 */
class HumanReadableTimeTest {

    @DisplayName("returns expected result for given seconds.")
    @ParameterizedTest(name = "{0} seconds -> human readable: {1}")
    @CsvSource({
            " 0, 00:00:00",
            " 1, 00:00:01",
            "59, 00:00:59",
            "60, 00:01:00",
            "61, 00:01:01",
            "3599, 00:59:59",
            "3600, 01:00:00"})
    void test(final int givenSeconds, final String expected) {
        assertThat(HumanReadableTime.makeReadable(givenSeconds)).isEqualTo(expected);
    }
    
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