package stringincrementer;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A Kata")
class KataTest {

	@Test @DisplayName("test empty")
	void testEmpty() {
		assertThat(Kata.incrementString("")).isEqualTo("1");
	}
	
	@Test @DisplayName("test new number")
	void test() {
		assertThat(Kata.incrementString("text")).isEqualTo("text1");
	}
	
	@Test @DisplayName("test increment")
	void testIncrement() {
		assertThat(Kata.incrementString("text1")).isEqualTo("text2");
	}
	
	@Test @DisplayName("test leading zeros.")
	void testLeadingZeros() {
		assertThat(Kata.incrementString("text01")).isEqualTo("text02");
	}

	@Test @DisplayName("test corner cases.")
	void testCornerCases() {
		assertThat(Kata.incrementString("text099")).isEqualTo("text100");
		assertThat(Kata.incrementString("text99")).isEqualTo("text100");
		assertThat(Kata.incrementString("snapshot-99")).isEqualTo("snapshot-100");
	}
}