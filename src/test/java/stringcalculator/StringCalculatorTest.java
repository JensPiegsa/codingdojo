package stringcalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A StringCalculator")
class StringCalculatorTest {

	StringCalculator stringCalculator = new StringCalculator();
	
	@Test @DisplayName("can survive with empty string")
	void canSurviveWithEmptyString() {
		assertThat(stringCalculator.add("")).isEqualTo(0);
	}
	
	@Test @DisplayName("can add one number")
	void canAddOneNumber() {
		assertThat(stringCalculator.add("1")).isEqualTo(1);
	}
	
	@Test @DisplayName("can add comma separated numbers")
	void canAddCommaSeparatedNumbers() {
		assertThat(stringCalculator.add("1,2")).isEqualTo(3);
	}
	
	@Test @DisplayName("can add numbers with new lines")
	void canAddNumbersWithNewLines() {
		assertThat(stringCalculator.add("1\n2,3")).isEqualTo(6);
	}
	
	@Test @DisplayName("can add numbers with special delimiter")
	void canAddNumbersWithSpecialDelimiter() {
		assertThat(stringCalculator.add("//;\n1;2")).isEqualTo(3);
	}

	@Test @DisplayName("throws exception for negative numbers")
	void throwsExceptionForNegativeNumbers() {
		assertThatThrownBy(() -> stringCalculator.add("1,-2,-5")).hasMessage("negatives not allowed: -2, -5");
	}
}