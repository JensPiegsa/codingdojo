package isaprime;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PrimeTest {
	@DisplayName("return false for Integers smaller than one")
	@ParameterizedTest(name = "return false for {0}")
	@CsvSource({"0","-1","-2","-3","-4","-5"})
	public void testIntSmallerThanOne(int i){
		assertFalse(Prime.isPrime(i));
	}

	@Test
	public void testBasic() {
		assertFalse("0  is not prime", Prime.isPrime(0));
		assertFalse("1  is not prime", Prime.isPrime(1));
		assertTrue ("2  is prime",     Prime.isPrime(2));
		assertTrue ("73 is prime",     Prime.isPrime(73));
		assertFalse("75 is not prime", Prime.isPrime(75));
		assertFalse("-1 is not prime", Prime.isPrime(-1));
	}
	
	@Test
	public void testPrime() {
		assertTrue("3 is prime", Prime.isPrime(3));
		assertTrue("5 is prime", Prime.isPrime(5));
		assertTrue("7 is prime", Prime.isPrime(7));
		assertTrue("41 is prime", Prime.isPrime(41));
		assertTrue("5099 is prime", Prime.isPrime(5099));
	}
	
	@Test
	public void testNotPrime() {
		assertFalse("4 is not prime", Prime.isPrime(4));
		assertFalse("6 is not prime", Prime.isPrime(6));
		assertFalse("8 is not prime", Prime.isPrime(8));
		assertFalse("9 is not prime", Prime.isPrime(9));
		assertFalse("45 is not prime", Prime.isPrime(45));
		assertFalse("-5 is not prime", Prime.isPrime(-5));
		assertFalse("-8 is not prime", Prime.isPrime(-8));
		assertFalse("-41 is not prime", Prime.isPrime(-41));
	}
}