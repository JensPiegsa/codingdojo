package aoc2021.day02;

import static aoc2021.day02.SubmarineCommand.down;
import static aoc2021.day02.SubmarineCommand.forward;
import static aoc2021.day02.SubmarineCommand.up;
import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiveTest {

	final Dive dive = new Dive();

	@Test @DisplayName("start at position zero.")
	void startAtPositionZero() {
		then(dive.getPositionProduct()).isEqualTo(0);
	}

	@Test @DisplayName("moving forward by 5 and down by 3 results in position 15.")
	void movingForwardBy5AndDownBy3ResultsInPosition15() {
		dive.move(forward, 5);
		dive.move(down, 3);

		then(dive.getPositionProduct()).isEqualTo(15);
	}

	@Test @DisplayName("test example.")
	void testExample() {
		dive.move(forward, 5);
		dive.move(down, 5);
		dive.move(forward, 8);
		dive.move(up, 3);
		dive.move(down, 8);
		dive.move(forward, 2);
		then(dive.getPositionProduct()).isEqualTo(150);
	}

	@Test @DisplayName("moving forward by 1 and up by 2 and down by 3 results in 3.")
	void movingForwardBy1AndUpBy2AndDownBy3ResultsIn3() {
		dive.move(forward, 1);
		dive.move(up, 2);
		dive.move(down, 3);

		then(dive.getPositionProduct()).isEqualTo(3);
	}

	@Test @DisplayName("test example commands.")
	void testExampleCommands() {
		dive.move(List.of(
				"forward 5",
				"down 5",
				"forward 8",
				"up 3",
				"down 8",
				"forward 2")
		);
		then(dive.getPositionProduct()).isEqualTo(150);
	}

	@Test @DisplayName("test with input file.")
	@SuppressWarnings("ConstantConditions")
	void testWithInputFile() {
		final String input = contentOf(getClass().getResource("input"));
		final List<String> commands = Arrays.asList(input.split("\n"));

		dive.move(commands);

		then(dive.getPositionProduct()).isEqualTo(2027977);
	}
}
