package test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// red - green - refactor

@DisplayName("FizzBuzz")
public class FizzBuzzTest {

	final FizzBuzz fizzBuzz = new FizzBuzz();

	@Test @DisplayName("returns '1' for 1.")
	void returns1For1() {
		final String result = fizzBuzz.run(1);
		assertThat(result).isEqualTo("1");
	}

	@Test @DisplayName("returns '2' for 2.")
	void returns2For2() {
		final String result = fizzBuzz.run(2);
		assertThat(result).isEqualTo("2");
	}

	@Test @DisplayName("returns 'Fizz' for 3.")
	void returnsFizzFor3() {
		final String result = fizzBuzz.run(3);
		assertThat(result).isEqualTo("Fizz");
	}

	@DisplayName("returns expected result for given input.")
	@ParameterizedTest(name = "returns {1} for {0}.")
	@CsvSource({"1,1", "2,2", "3,Fizz"})
	void test(int i, String result) {
		assertThat(fizzBuzz.run(i)).isEqualTo(result);
	}
}
