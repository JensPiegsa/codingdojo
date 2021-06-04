package stringcalculator;

import static org.assertj.core.api.Assertions.assertThat;

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
}