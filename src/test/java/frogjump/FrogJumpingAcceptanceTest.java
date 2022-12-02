package frogjump;

import java.util.Arrays;
import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

// https://www.codewars.com/kata/536950ffc8a5ca9982001371/

@DisplayName("A FrogJumping (Acceptance Test)")
class FrogJumpingAcceptanceTest {

	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{new int[]{1, 2, 2, -1}, 4},
				{new int[]{1, 2, 1, 5}, 3},
				{new int[]{1, 1, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1}, 9},
				{new int[]{1, 2, 3, 1, -2, 1}, 6},
				{new int[]{1, 1, 1, 1}, 4},
				{new int[]{-1, -1, -2}, 1},
				{new int[]{-3}, 1},
				{new int[]{1, -1}, -1},
				{new int[]{1, 2, 1, 2, -3, -4}, -1},
				{new int[]{1, 0, 2}, -1}
		});
	}

	@ParameterizedTest
	@MethodSource("data")
	public void should_test_solution(int[] input, int expected) {
		Assertions.assertEquals(expected, FrogJumping.solution(input), Arrays.toString(input));
	}


}