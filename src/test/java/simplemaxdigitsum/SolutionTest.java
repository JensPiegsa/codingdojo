package simplemaxdigitsum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.runners.JUnit4;

public class SolutionTest {

	/*
	In this Kata, you will be given an integer n and your task will be to return the 
	largest integer that is <= n and has the highest digit sum.

	For example:
	
	solve(100) = 99. Digit Sum for 99 = 9 + 9 = 18. No other number <= 100 has a higher digit sum.
	solve(10) = 9
	solve(48) = 48. Note that 39 is also an option, but 48 is larger.
	Input range is 0 < n < 1e11
	
	More examples in the test cases.
	 */
	@Test
	public void basicTests() {
		assertEquals(1L, Solution.solve(1L));
		assertEquals(2L, Solution.solve(2L));
		assertEquals(18L, Solution.solve(18L));
		assertEquals(48L, Solution.solve(48L));
		assertEquals(99L, Solution.solve(100L));
		assertEquals(9L, Solution.solve(10L));
		assertEquals(99L, Solution.solve(110L));
		assertEquals(1999L, Solution.solve(2090L));
		assertEquals(999999999989L, Solution.solve(999999999992L));
	}
}