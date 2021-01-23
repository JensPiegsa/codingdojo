package advent2020.day03;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TraverseRouteTreeCounterTest {

	TraverseRouteTreeCounter traverseRouteTreeCounter;
	
	@Test @DisplayName("easy way down")
	void easyWayDown() {
		// given
		String map = contentOf(getClass().getResource("easy.txt"));
		traverseRouteTreeCounter = new TraverseRouteTreeCounter(map);
		
		// when
		final int expectedTrees = traverseRouteTreeCounter.countTreesCollisions(3,1);

		// then
		then(expectedTrees).isEqualTo(7);
	}
	
	@Test @DisplayName("large map")
	void largeMap() {
		/// given
		String map = contentOf(getClass().getResource("input.txt"));
		traverseRouteTreeCounter = new TraverseRouteTreeCounter(map);

		// when
		final int expectedTrees = traverseRouteTreeCounter.countTreesCollisions(3,1);

		// then
		then(expectedTrees).isEqualTo(272);
	}
	
	@Test @DisplayName("multiply tree collision on five standard traverse routes")
	void multiplyTreeCollisionOnFiveStandardTraverseRoutes() {
		// given
		String map = contentOf(getClass().getResource("input.txt"));
		traverseRouteTreeCounter = new TraverseRouteTreeCounter(map);

		// when
		long all = traverseRouteTreeCounter.multiplyTreesOnStandardTraverseRoutes();

		// then
		then(all).isEqualTo(3898725600L);
	}

}