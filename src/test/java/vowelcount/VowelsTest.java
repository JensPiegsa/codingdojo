package vowelcount;

import static org.junit.jupiter.api.Assertions.*;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

class VowelsTest {

	@Property(tries = 1)
	boolean emptyStringContainsNoVowels(@ForAll("emptyString") String empty) {
		return Vowels.getCount(empty) == 0;
	}

	@Property
	boolean vowelCountOfStringsContainingOnlyVowelsEqualsStringLength(@ForAll("vowels") String s) {
		return Vowels.getCount(s) == s.length();
	}

	@Property
	boolean vowelCountOfStringsContainingOnlyConsonantsIsZero(@ForAll("consonants") String s) {
		return Vowels.getCount(s) == 0;
	}

	@Property
	boolean vowelCountEqualsExpected1(@ForAll("consonants") String consonants,
	                                 @ForAll("vowels") String vowels) {
		return Vowels.getCount(vowels + consonants) == vowels.length();
	}

	@Property
	boolean vowelCountEqualsExpected2(@ForAll("consonants") String consonants,
	                                  @ForAll("vowels") String vowels) {
		return Vowels.getCount(consonants + vowels) == vowels.length();
	}

	@Provide
	Arbitrary<String> consonants() {
		return Arbitraries.strings().excludeChars('a','e','i','o','u');
	}

	@Provide
	Arbitrary<String> vowels() {
		return Arbitraries.strings().withChars("aeiou");
	}

	@Provide
	Arbitrary<String> emptyString() {
		return Arbitraries.strings().ofLength(0);
	}

}