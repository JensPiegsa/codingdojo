package scramblies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;

class ScrambliesTest {

    @Nested @DisplayName("acceptance test.")
    class AcceptanceTest {

        private static void testing(boolean actual, boolean expected) {
            assertEquals(expected, actual);
        }

        @Test @Order(1)
        public void sampleTests() {
            testing(Scramblies.scramble("rkqodlw","world"), true);
            testing(Scramblies.scramble("cedewaraaossoqqyt","codewars"),true);
            testing(Scramblies.scramble("katas","steak"),false);
            testing(Scramblies.scramble("scriptjavx","javascript"),false);
            testing(Scramblies.scramble("scriptingjava","javascript"),true);
            testing(Scramblies.scramble("scriptsjava","javascripts"),true);
            testing(Scramblies.scramble("javscripts","javascript"),false);
            testing(Scramblies.scramble("aabbcamaomsccdd","commas"),true);
            testing(Scramblies.scramble("commas","commas"),true);
            testing(Scramblies.scramble("sammoc","commas"),true);
        }

        @Test @Order(2)
        public void largeTest() {

            String s1 = "abcdefghijklmnopqrstuvwxyz".repeat(10_000);
            String s2 = "zyxcba".repeat(9_000);

            testing(Scramblies.scramble(s1, s2), true);
        }
    }
}