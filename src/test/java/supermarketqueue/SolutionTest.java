package supermarketqueue;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A Solution")
class SolutionTest {
	
	//	https://www.codewars.com/kata/57b06f90e298a7b53d000a86/train/java

	@Test @DisplayName("checkout time expected to be zero with no customers.")
	void checkoutTimeExpectedToBeZeroWithNoCustomers() {
		final int[] customerTimesInMinutes = {};
		final int numberOfTills = 1;

		final int totalCheckoutTimeInMinutes = Solution.solveSuperMarketQueue(customerTimesInMinutes, numberOfTills);

		then(totalCheckoutTimeInMinutes).isZero();
	}
	
	@Test @DisplayName("checkout time expected to be five with one customer.")
	void checkoutTimeExpectedToBeFiveWithOneCustomer() {
		assertThat(Solution.solveSuperMarketQueue(new int[]{5}, 1)).isEqualTo(5);
	}

	@Test @DisplayName("checkout time expected to be four with two customers.")
	void checkoutTimeExpectedToBeFourWithTwoCustomers() {
		assertThat(Solution.solveSuperMarketQueue(new int[]{1, 3}, 1)).isEqualTo(4);
	}

	@Test @DisplayName("checkout time expected to be three with two customers and two tills.")
	void checkoutTimeExpectedToBeThreeWithTwoCustomersAndTwoTills() {
		assertThat(Solution.solveSuperMarketQueue(new int[]{1, 3}, 2)).isEqualTo(3);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testNormalCondition() {
		assertEquals(9, Solution.solveSuperMarketQueue(new int[]{2, 2, 3, 3, 4, 4}, 2));
	}

	@Test
	public void testEmptyArray() {
		assertEquals(0, Solution.solveSuperMarketQueue(new int[]{}, 1));
	}

	@Test
	public void testSingleTillManyCustomers() {
		assertEquals(15, Solution.solveSuperMarketQueue(new int[]{1, 2, 3, 4, 5}, 1));
	}
}