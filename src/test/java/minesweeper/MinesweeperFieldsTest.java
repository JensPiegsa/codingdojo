package minesweeper;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Minesweeper fields")
class MinesweeperFieldsTest {

	@Test @DisplayName("pass the acceptance test.")
	void acceptanceTest() {
		
		final String input = "4 4\n" +
				"*...\n" +
				"....\n" +
				".*..\n" +
				"....\n" +
				"3 5\n" +
				"**...\n" +
				".....\n" +
				".*...\n" +
				"0 0";
		final MinesweeperFields minesweeperFields = new MinesweeperFields();

		final String output = minesweeperFields.printNumbers(input);

		then(output).isEqualTo("Field #1:\n" +
				"*100\n" +
				"2210\n" +
				"1*10\n" +
				"1110\n" +
				"\n" +
				"Field #2:\n" +
				"**100\n" +
				"33200\n" +
				"1*100");
	}
	
	@Test @DisplayName("single field #1 has expected numbers.")
	void singleField1HasExpectedNumbers() {
		final String input = "4 4\n" +
				"*...\n" +
				"....\n" +
				".*..\n" +
				"....\n" +
				"0 0";
		final MinesweeperFields minesweeperFields = new MinesweeperFields();

		final String output = minesweeperFields.printNumbers(input);

		then(output).isEqualTo("Field #1:\n" +
				"*100\n" +
				"2210\n" +
				"1*10\n" +
				"1110");

	}
	
	@Test @DisplayName("single field #2 has expected numbers.")
	void singleField2HasExpectedNumbers() {
		final String input = "3 5\n" +
				"**...\n" +
				".....\n" +
				".*...\n" +
				"0 0";
		final MinesweeperFields minesweeperFields = new MinesweeperFields();

		final String output = minesweeperFields.printNumbers(input);

		then(output).isEqualTo("Field #1:\n" +
				"**100\n" +
				"33200\n" +
				"1*100");
	}
}
