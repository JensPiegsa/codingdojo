package yatzy;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class YatzyTest {

	// see: https://sammancoaching.org/kata_descriptions/yatzy.html
	
	@DisplayName("roll with category: chance")
	@ParameterizedTest()
	@MethodSource("rollByChance")
	void rollWithCategoryChance(List<Integer> dices, int expectedScore) {
		assertThat(Yatzy.chance(dices)).isEqualTo(expectedScore);
	}

	static Stream<Arguments> rollByChance() {
		return Stream.of(
				Arguments.of(asList(1, 1, 3, 3, 6), 14),
				Arguments.of(asList(4, 5, 5, 6, 1), 21)
		);
	}

	@DisplayName("roll with category: yatzy")
	@ParameterizedTest()
	@MethodSource("rollByYatzy")
	void rollWithCategoryYatzy(List<Integer> dices, int expectedScore) {
		assertThat(Yatzy.yatzy(dices)).isEqualTo(expectedScore);
	}

	static Stream<Arguments> rollByYatzy() {
		return Stream.of(
				Arguments.of(asList(1, 1, 1, 1, 1), 50),
				Arguments.of(asList(2, 2, 2, 2, 2), 50),
				Arguments.of(asList(1, 1, 1, 2, 1), 0)
		);
	}

	@Test
	@DisplayName("roll with category: ones")
	void rollWithCategoryOnes() {
		final List<Integer> dices = asList(3, 3, 3, 4, 5);
		final int actual = Yatzy.ones(dices);
		assertThat(actual).isEqualTo(0);
	}

	@Test
	@DisplayName("roll with category: twos")
	void rollWithCategoryTwos() {
		final List<Integer> dices = asList(2, 3, 2, 5, 1);
		final int actual = Yatzy.twos(dices);
		assertThat(actual).isEqualTo(4);
	}
	
	@Test @DisplayName("roll with category: threes")
	void rollWithCategoryThrees() {
		final List<Integer> dices = asList(3, 3, 3, 4, 5);
		final int actual = Yatzy.threes(dices);
		assertThat(actual).isEqualTo(9);
	}

	@Test
	@DisplayName("roll with category: fours")
	void rollWithCategoryFours() {
		final List<Integer> dices = asList(1, 1, 2, 4, 4);
		final int actual = Yatzy.fours(dices);
		assertThat(actual).isEqualTo(8);
	}
	
	@Test @DisplayName("roll with category: fives")
	void rollWithCategoryFives() {
		final List<Integer> dices = asList(3, 3, 5, 4, 5);
		final int actual = Yatzy.fives(dices);
		assertThat(actual).isEqualTo(10);
	}
	
	@Test @DisplayName("roll with category: sixes")
	void rollWithCategorySixes() {
		final List<Integer> dices = asList(6, 6, 5, 4, 6);
		final int actual = Yatzy.sixes(dices);
		assertThat(actual).isEqualTo(18);
	}

	@DisplayName("roll with category: pair")
	@ParameterizedTest()
	@MethodSource("rollByPair")
	void rollWithCategoryPair(List<Integer> dices, int expectedScore) {
		assertThat(Yatzy.pair(dices)).isEqualTo(expectedScore);
	}

	static Stream<Arguments> rollByPair() {
		return Stream.of(
				Arguments.of(asList(3,3,3,4,4), 8),
				Arguments.of(asList(1,1,6,2,6), 12),
				Arguments.of(asList(3,3,3,4,1), 6),
				Arguments.of(asList(3,3,3,3,1), 6)
		);
	}
}