package word.wrap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *  Word wrap
 *
 * This kata is from Robert Martin and his blog includes a solution in Java [1] .
 * Problem Description
 *
 * You write a class called Wrapper, that has a single static function named wrap that
 * takes two arguments, a string, and a column number. The function returns the string,
 * but with line breaks inserted at just the right places to make sure that no line is
 * longer than the column number. You try to break lines at word boundaries.
 *
 * Like a word processor, break the line by replacing the last space in a line with a newline.
 */
class WrapperTest {

	@Test @DisplayName("accept empty input.")
	void acceptEmptyInput() {
		assertThat(Wrapper.wrap("", 1)).isEmpty();
	}
	
	@Test @DisplayName("accepts short input without need to wrap.")
	void acceptsShortInputWithoutNeedToWrap() {
		assertThat(Wrapper.wrap("short input", 11)).isEqualTo("short input");
	}

	@Test @DisplayName("accepts short input with need to wrap.")
	void acceptsShortInputWithNeedToWrap() {
		assertThat(Wrapper.wrap("short input", 10)).isEqualTo("short\ninput");
	}
}