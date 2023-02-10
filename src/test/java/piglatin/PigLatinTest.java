package piglatin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PigLatinTest {
    
    // "nullay nullay!"

    @Test @DisplayName("empty input")
    void emptyInput() {
    	assertThat(PigLatin.pigIt("")).isEqualTo("");
    }
    
    @Test @DisplayName("space")
    void space() {
    	assertThat(PigLatin.pigIt(" ")).isEqualTo(" ");
    }

    @Test @DisplayName("simple case")
    void simpleCase() {
    	assertThat(PigLatin.pigIt(" !")).isEqualTo(" !");
    }

    @Test @DisplayName("punctuation")
    void punctuation() {
    	assertThat(PigLatin.pigIt(",!,")).isEqualTo(",!,");
    }
    
    @Test @DisplayName("single uppercase word")
    void singleUppercaseWord() {
        assertThat(PigLatin.pigIt("Hello")).isEqualTo("elloHay");
    }

    @Test @DisplayName("two words")
    void twoWords() {
        assertEquals("elloHay orldway", PigLatin.pigIt("Hello world"));
    }

    @Test @DisplayName("two words with two spaces")
    void twoWords2() {
        assertEquals("elloHay  orldway", PigLatin.pigIt("Hello  world"));
    }

    @Test @DisplayName("two words with two spaces and leading space")
    void twoWords3() {
        assertEquals(" elloHay  orldway", PigLatin.pigIt(" Hello  world"));
    }

    @Test
    public void FixedTests() {
        assertEquals("igPay atinlay siay oolcay", PigLatin.pigIt("Pig latin is cool"));
        assertEquals("hisTay siay ymay tringsay", PigLatin.pigIt("This is my string"));
        assertEquals("elloHay orldway !", PigLatin.pigIt("Hello world !"));
    }
}