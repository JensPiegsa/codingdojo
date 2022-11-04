package binomialexpansion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * <a href="https://www.codewars.com/kata/540d0fdd3b6532e5c3000b5b/">https://www.codewars.com/kata/540d0fdd3b6532e5c3000b5b/</a>
 */
public class BinomialExpansionTest {
	
	@Test @DisplayName("test")
	void test() {
		then(KataSolution.expand("(x+1)^2")).isEqualTo("x^2+2x+1");
	}
	
	@Test @DisplayName("test2")
	void test2() {
		then(KataSolution.expand("(p-1)^3")).isEqualTo("p^3-3p^2+3p-1");
	}
	
	@Test @DisplayName("test3")
	void test3() {
		then(KataSolution.expand("(r+0)^203")).isEqualTo("r^203");
		then(KataSolution.expand("(5r+0)^3")).isEqualTo("125r^3");
	}
	
	@ParameterizedTest
	@DisplayName("test faculty")
	@CsvSource({"0,1", "1, 1", "2, 2", "3, 6", "4, 24", "5, 120"})
	void testFaculty(long k, long expected) {
		assertThat(KataSolution.faculty(k)).isEqualTo(expected);
	}
	

	@Test @DisplayName("binomialCoefficient")
	void binomialCoefficient() {
		assertThat(KataSolution.binomialCoefficient(3, 2)).isEqualTo(6L / (1L * 2L));
	}
}
