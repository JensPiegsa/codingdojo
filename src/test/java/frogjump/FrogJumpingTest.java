package frogjump;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A FrogJumping")
class FrogJumpingTest {

	@Test @DisplayName("frog can not jump out.")
	void frogCanNotJumpOut() {
		final int[] input = {0};
		final int actual = FrogJumping.solution(input);
		assertThat(actual).isEqualTo(-1);
	}
	
	@Test @DisplayName("frog can jump out of one element array to the right.")
	void frogCanJumpOutOfOneElementArrayToTheRight() {
		final int[] input = {1};
		final int actual = FrogJumping.solution(input);
		assertThat(actual).isEqualTo(1);
	}

	@Test @DisplayName("frog can jump out of a one element array to the left.")
	void frogCanJumpOutOfAOneElementArrayToTheLeft() {
		final int[] input = {-1};
		final int actual = FrogJumping.solution(input);
		assertThat(actual).isEqualTo(1);
	}
	
	@Test @DisplayName("frog can jump out of a two element array to the right.")
	void frogCanJumpOutOfATwoElementArrayToTheRight() {
		final int[] input = {1, 2};
		final int actual = FrogJumping.solution(input);
		assertThat(actual).isEqualTo(2);
	}
	
	@Test @DisplayName("frog can not jump out of a cyclic two element array.")
	void frogCanNotJumpOutOfACyclicTwoElementArray() {
		final int[] input = {1, -1};
		final int actual = FrogJumping.solution(input);
		assertThat(actual).isEqualTo(-1);
	}
}