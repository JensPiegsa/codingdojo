package humanreadableduration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TimeFormatterTest {

    // https://www.codewars.com/kata/52742f58faf5485cae000b9a/train/java

    @Test @DisplayName("returns 'now' for 0.")
    void test() {
        assertThat(TimeFormatter.formatDuration(0)).isEqualTo("now");
    }
    
    @Test @DisplayName("returns '1 second' for 1.")
    void returns1SecondFor1() {
        assertThat(TimeFormatter.formatDuration(1)).isEqualTo("1 second");
    }

    @Test @DisplayName("returns '2 seconds' for 2.")
    void returns2SecondsFor2() {
        assertThat(TimeFormatter.formatDuration(2)).isEqualTo("2 seconds");
    }

    @Test @DisplayName("returns '1 minute' for 60.")
    void returns1MinuteFor60() {
        assertThat(TimeFormatter.formatDuration(60)).isEqualTo("1 minute");
    }

    @Test @DisplayName("returns '1 hour' for 3600.")
    void returns1HourFor3600() {
        assertThat(TimeFormatter.formatDuration(3600)).isEqualTo("1 hour");
    }

    @Test @DisplayName("returns '1 day' for 86400.")
    void returns1DayFor86400() {
        assertThat(TimeFormatter.formatDuration(86400)).isEqualTo("1 day");
    }

    @Test @DisplayName("returns '1 minute and 1 second' for 61.")
    void returns1MinuteAnd1SecondFor61() {
        assertThat(TimeFormatter.formatDuration(61)).isEqualTo("1 minute and 1 second");
    }
    
    @Nested @DisplayName("combinations")
    class Combinations {
    	
        @Test @DisplayName("combine() returns 'a' for list of (a).")
        void test1() {
            assertThat(TimeFormatter.combine(List.of("a"))).isEqualTo("a");
        }
        
        @Test @DisplayName("combine() returns 'a and b' for list of (a, b).")
        void test2() {
            assertThat(TimeFormatter.combine(List.of("a", "b"))).isEqualTo("a and b");
        }        
        
        @Test @DisplayName("combine() returns 'a, b and c' for list of (a, b, c).")
        void test3() {
            assertThat(TimeFormatter.combine(List.of("a", "b", "c"))).isEqualTo("a, b and c");
        }
        
        @Test @DisplayName("combine() returns 'a, b, c and d' for list of (a, b, c, d).")
        void test4() {
            assertThat(TimeFormatter.combine(List.of("a", "b", "c", "d"))).isEqualTo("a, b, c and d");
        }
    }

    @Nested
    class AcceptanceTest {

        @Test
        public void exampleTests() {
            assertEquals("1 second", TimeFormatter.formatDuration(1));
            assertEquals("1 minute and 2 seconds", TimeFormatter.formatDuration(62));
            assertEquals("2 minutes", TimeFormatter.formatDuration(120));
            assertEquals("1 hour", TimeFormatter.formatDuration(3600));
            assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.formatDuration(3662));
        }
    }
}