package arewealternate;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * <a href="https://www.codewars.com/kata/59325dc15dbb44b2440000af/">https://www.codewars.com/kata/59325dc15dbb44b2440000af/</a>
 */
@SuppressWarnings("JUnitTestMethodWithNoAssertions")
@DisplayName("A Solution")
class SolutionTest {

	@Property
	boolean firstLetterVowelSecondLetterConsonant(@ForAll("vowels") final char vowel,
	                                              @ForAll("consonants") final char consonant) {
		final String word = String.valueOf(vowel) + consonant;
		return Solution.isAlt(word);
	}
	
	@Property
	boolean firstLetterConsonantSecondLetterVowel(@ForAll("consonants") final char consonant,
	                                              @ForAll("vowels") final char vowel) {
		final String word = String.valueOf(consonant) + vowel;
		return Solution.isAlt(word);
	}
	
	@Property
	boolean twoVowels(@ForAll("vowels") final char vowelOne, 
	                  @ForAll("vowels") final char vowelTwo) {
		final String word = String.valueOf(vowelOne) + vowelTwo;
		return !Solution.isAlt(word);
	}
	
	@Property
	boolean twoConsonants(@ForAll("consonants") final char consonantsOne, 
	                  @ForAll("consonants") final char consonantsTwo) {
		final String word = String.valueOf(consonantsOne) + consonantsTwo;
		return !Solution.isAlt(word);
	}

	@Provide
	Arbitrary<Integer> integers() {
		return Arbitraries.integers().between(2, 10);
	}


	@Provide
	Arbitrary<Character> vowels() {
		return Arbitraries.chars().with('a', 'e', 'i', 'o', 'u');
	}

	@Provide
	Arbitrary<Character> consonants() {
		return Arbitraries.chars().range('a', 'z').filter(c -> !Set.of('a', 'e', 'i', 'o', 'u').contains(c));
	}

	@Nested
	@DisplayName("acceptance tests")
	class AcceptanceTests {

		@Test
		public void exampleTests() {
			assertEquals(true, Solution.isAlt("amazon"));
			assertEquals(false, Solution.isAlt("apple"));
			assertEquals(true, Solution.isAlt("banana"));
		}
	}
}