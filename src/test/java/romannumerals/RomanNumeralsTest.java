package romannumerals;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class RomanNumeralsTest {

	@ParameterizedTest(name = "{0} -> {1}") @DisplayName("returns expected String for given long.")
	@MethodSource("testData")
	void returnsExpectedStringForGivenLong(final long arabicNumber, final String expectedRomanNumber) {
		final String romanNumber = new RomanNumerals().toRomanNumber(arabicNumber);
		then(romanNumber).isEqualTo(expectedRomanNumber);
	}

	private static Stream<Arguments> testData() {
		return Stream.of(
				arguments(1, "I"),
				arguments(2, "II"),
				arguments(3, "III"),
				arguments(4, "IV"),
				arguments(5, "V"),
				arguments(10, "X"),
				arguments(20, "XX"),
				arguments(30, "XXX")
		);
	}

}
