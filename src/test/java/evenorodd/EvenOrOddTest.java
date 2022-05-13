package evenorodd;

import static org.junit.jupiter.api.Assertions.*;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import org.junit.jupiter.api.Test;

class EvenOrOddTest {

	@Property
	boolean numbersEndingWithEvenDigitAreEven(@ForAll("number") int number,
	                                          @ForAll("evenDigit") int evenDigit) {
		return "Even".equals(EvenOrOdd.even_or_odd(number * 10 + evenDigit));
	}
	
	@Property
	boolean numbersEndingWithOddDigitAreOdd(@ForAll("number") int number,
	                                          @ForAll("oddDigit") int oddDigit) {
		return "Odd".equals(EvenOrOdd.even_or_odd(number * 10 + oddDigit));
	}
	
	@Provide
	Arbitrary<Integer> evenDigit() {
		return Arbitraries.integers().between(0, 9).filter(l -> l % 2 == 0);
	}
	
	@Provide
	Arbitrary<Integer> oddDigit() {
		return Arbitraries.integers().between(0, 9).filter(l -> l % 2 != 0);
	}

	@Provide
	Arbitrary<Integer> number() {
		return Arbitraries.integers().between(Integer.MIN_VALUE / 10, Integer.MAX_VALUE / 10);
	}


	@Test
	public void testEvenOrOdd() {
		EvenOrOdd eoo = new EvenOrOdd();
		assertEquals("Even", eoo.even_or_odd(6));
		assertEquals("Odd", eoo.even_or_odd(7));
		assertEquals("Even", eoo.even_or_odd(0));
		assertEquals("Odd", eoo.even_or_odd(-1));
	}
}