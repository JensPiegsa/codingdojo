package numbersinwords;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("A number to words converter")
class NumberInWordsTest {

	NumberInWords numberInWords = new NumberInWords();

	@DisplayName("converts given number to expected word.")
	@ParameterizedTest(name = "returns <{1}> for {0}.")
	@CsvSource({
			"0,zero",
			"1,one",
			"2,two",
			"3,three",
			"4,four",
			"5,five",
			"6,six",
			"7,seven",
			"8,eight",
			"9,nine"})
	void oneDigit(int givenNumber, String expectedWords) {
		assertThat(numberInWords.toWords(givenNumber)).isEqualTo(expectedWords);
	}

	@DisplayName("converts given number to expected word.")
	@ParameterizedTest(name = "returns <{1}> for {0}.")
	@CsvSource({
			"10,ten",
			"11,eleven",
			"12,twelve",
			"13,thirteen",
			"14,fourteen",
			"15,fifteen",
			"16,sixteen",
			"17,seventeen",
			"18,eighteen",
			"19,nineteen"})
	void twoDigitsTeens(int givenNumber, String expectedWords) {
		assertThat(numberInWords.toWords(givenNumber)).isEqualTo(expectedWords);
	}

	@Test
	@DisplayName("raises IllegalArgumentException for -1.")
	void raisesIllegalArgumentExceptionForMinusOne() {
		assertThrows(IllegalArgumentException.class, () -> numberInWords.toWords(-1));

	}
	
	@DisplayName("converts tens from 20 to 90.")
	@ParameterizedTest(name = "returns <{1}> for {0}.")
	@CsvSource({
			"20,twenty",
			"30,thirty",
			"40,forty",
			"50,fifty",
			"60,sixty",
			"70,seventy",
			"80,eighty",
			"90,ninety"})
	void convertsTensFrom20To99(int givenNumber, String expectedWords) {
		assertThat(numberInWords.toWords(givenNumber)).isEqualTo(expectedWords);
	}

	@DisplayName("calculate tens digit.")
	@ParameterizedTest(name = "returns <{1}> for {0}.")
	@CsvSource({
			"1,0",
			"30,3",
			"40,4",
			"55,5",
			"69,6"})
	void calculateTensNumber(int givenNumber, int expectedDigitValue) {
		assertThat(numberInWords.getTens(givenNumber)).isEqualTo(expectedDigitValue);
	}
	
	@Test @DisplayName("converts 42.")
	void converts42() {
		assertThat(numberInWords.toWords(42)).isEqualTo("forty two");
	}
}