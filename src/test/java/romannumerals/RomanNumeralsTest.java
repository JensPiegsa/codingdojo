package romannumerals;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class RomanNumeralsTest {

	@ParameterizedTest(name = "{0} -> {1}") @DisplayName("returns expected String for given long.")
	@MethodSource("webData")
	void returnsExpectedStringForGivenLong(final long arabicNumber, final String expectedRomanNumber) {
		final String romanNumber = new RomanNumerals().toRomanNumber(arabicNumber);
		then(romanNumber).isEqualTo(expectedRomanNumber);
	}

	@Disabled("unfinished")
	@ParameterizedTest(name = "{1} -> {0}") @DisplayName("returns expected long for given String.")
	@MethodSource("webData")
	void returnsExpectedNumberForGivenString(final long expectedArabicNumber, final String romanNumber) {
		final long arabicNumber = new RomanNumerals().toNumber(romanNumber);
		then(arabicNumber).isEqualTo(expectedArabicNumber);
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

	private static Stream<Arguments> webData() {
		return Stream.of(
				arguments(1, "I"),
				arguments(2, "II"),
				arguments(3, "III"),
				arguments(4, "IV"),
				arguments(5, "V"),
				arguments(6, "VI"),
				arguments(7, "VII"),
				arguments(8, "VIII"),
				arguments(9, "IX"),
				arguments(10, "X"),
				arguments(11, "XI"),
				arguments(20, "XX"),
				arguments(22, "XXII"),
				arguments(30, "XXX"),
				arguments(33, "XXXIII"),
				arguments(40, "XL"),
				arguments(44, "XLIV"),
				arguments(50, "L"),
				arguments(55, "LV"),
				arguments(60, "LX"),
				arguments(66, "LXVI"),
				arguments(70, "LXX"),
				arguments(77, "LXXVII"),
				arguments(80, "LXXX"),
				arguments(88, "LXXXVIII"),
				arguments(90, "XC"),
				arguments(99, "XCIX"),
				arguments(100, "C"),
				arguments(119, "CXIX"),
				arguments(200, "CC"),
				arguments(214, "CCXIV"),
				arguments(300, "CCC"),
				arguments(304, "CCCIV"),
				arguments(400, "CD"),
				arguments(468, "CDLXVIII"),
				arguments(500, "D"),
				arguments(545, "DXLV"),
				arguments(600, "DC"),
				arguments(633, "DCXXXIII"),
				arguments(700, "DCC"),
				arguments(774, "DCCLXXIV"),
				arguments(800, "DCCC"),
				arguments(895, "DCCCXCV"),
				arguments(900, "CM"),
				arguments(921, "CMXXI"),
				arguments(1000, "M")
		);
	}

	@Test @DisplayName("no assert, but demo")
	void noAssertButDemo() {
		RomanNumerals romanNumerals = new RomanNumerals();

		for(int i = 1; i <= 4000; i++) {
			System.out.println("arguments(" + i + ", \"" + romanNumerals.toRomanNumber(i) + "\"),  ");
		}
	}

	/*
	   1 = I;    1.000 - M;     1.000.000 - (M)
	   5 - V;    5.000 - (V);   5.000.000 - ((V))
	  10 - X;   10.000 - (X);  10.000.000 - ((X))
	  50 - L;   50.000 - (L);  ...
	 100 - C;  100.000 - (C);
	 500 - D;  500.000 - (D)


	1. Regel: I steht nur vor V und X
	2. Regel: X steht nur vor L und C
	3. Regel: C steht nur vor D und M

	Weg 1: Ziffernweise
	1234 -> 1234 -> 1234 -> 1234 --> MCCXXXIV
	   ^      ^      ^      ^
	   IV     XXX    CC     M

	Weg 2: Modulo 1000, 500, 100, 50, 10    <- *

	 */

}
