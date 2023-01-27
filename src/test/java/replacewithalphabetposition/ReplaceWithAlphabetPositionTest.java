package replacewithalphabetposition;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReplaceWithAlphabetPositionTest {

    @Test @DisplayName("returns empty string when empty.")
    void returnsEmptyStringWhenEmpty() {
    	final String actual = ReplaceWithAlphabetPosition.toNumerical("");
        then(actual).isEmpty();
    }
    
    @Test @DisplayName("returns expected numbers for given string.")
    void returnsExpectedNumbersForGivenString() {
        final String actual = ReplaceWithAlphabetPosition.toNumerical("abc");
        then(actual).isEqualTo("1 2 3");
    }

    @Test @DisplayName("works with upper and lower case.")
    void worksWithUpperAndLowerCase() {
        final String actual = ReplaceWithAlphabetPosition.toNumerical("ABCabc");
        then(actual).isEqualTo("1 2 3 1 2 3");
    }
}