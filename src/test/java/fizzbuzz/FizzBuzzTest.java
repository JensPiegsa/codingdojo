package fizzbuzz;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// red - green - refactor

@DisplayName("FizzBuzz")
public class FizzBuzzTest {

	final FizzBuzz fizzBuzz = new FizzBuzz();

	@DisplayName("returns expected result for given input.")
	@ParameterizedTest(name = "returns {1} for {0}.")
	@CsvSource({"1,1", "2,2", "3,Fizz", "5,Buzz", "6,Fizz", "10,Buzz", "15,FizzBuzz"})
	void test(int i, String result) {
		assertThat(fizzBuzz.run(i)).isEqualTo(result);
	}

	// TODO try PBT

}
