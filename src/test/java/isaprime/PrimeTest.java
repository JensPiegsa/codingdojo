package isaprime;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PrimeTest {

	@DisplayName("return false for Integers smaller than one")
	@ParameterizedTest(name = "return false for {0}")
	@CsvSource({"0", "-1", "-2", "-3", "-4", "-5"})
	public void testIntSmallerThanOne(int i) {
		assertFalse(Prime.isPrime(i));
	}

	@DisplayName("return true for known primes")
	@ParameterizedTest(name = "return true for {0}")
	@CsvSource({"3", "5"})
	public void testPrime(int i) {
		assertTrue(Prime.isPrime(i));
	}

	@DisplayName("return false for known non-primes")
	@ParameterizedTest(name = "return false for {0}")
	@CsvSource({"2", "4"})
	public void testNonPrime(int i) {
		assertFalse(Prime.isPrime(i));
	}
}