package supermarketqueue;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A Solution")
class SolutionTest {




































































	@Test
	public void testNormalCondition() {
		assertEquals(9, Solution.solveSuperMarketQueue(new int[] { 2, 2, 3, 3, 4, 4 }, 2));
	}

	@Test
	public void testEmptyArray() {
		assertEquals(0, Solution.solveSuperMarketQueue(new int[] {}, 1));
	}

	@Test
	public void testSingleTillManyCustomers() {
		assertEquals(15, Solution.solveSuperMarketQueue(new int[] { 1, 2, 3, 4, 5 }, 1));
	}
}