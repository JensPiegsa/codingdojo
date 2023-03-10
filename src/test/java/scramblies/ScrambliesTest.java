package scramblies;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class ScrambliesTest {

    // https://www.codewars.com/kata/55c04b4cc56a697bb0000048/train/java

    @Test @DisplayName("returns false when no scrambled letters are provided.")
    void returnsFalseWhenNoScrambledLettersAreProvided() {
        assertThat(Scramblies.scramble("", "abc")).isFalse();
    }
    
    @Test @DisplayName("returns true when target is empty")
    void returnsTrueWhenTargetIsEmpty() {
        assertThat(Scramblies.scramble("unused", "")).isTrue();
    }
    
    @Test @DisplayName("returns true when scrambled letters and target equal.")
    void returnsTrueWhenScrambledLettersAndTargetEqual() {
        assertThat(Scramblies.scramble("letters", "letters")).isTrue();
    }

    @Test @DisplayName("returns true when scrambled letters contain all target letters.")
    void returnsTrueWhenScrambledLettersContainAllTargetLetters() {
        assertThat(Scramblies.scramble("tletrse", "letters")).isTrue();
    }

    @Nested @DisplayName("acceptance test.")
    class AcceptanceTest {

        private static void testing(boolean actual, boolean expected) {
            assertEquals(expected, actual);
        }

        @Test
        @Order(1)
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