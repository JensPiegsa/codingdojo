package minesweeper;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A MinesweeperField")
class MinesweeperFieldTest {
	
	@Test @DisplayName("returns field with expected numbers.")
	void returnsFieldWithExpectedNumbers() {
		final char[][] data = {
				{'*', '.'},	
				{'.', '*'}	
		};
		final MinesweeperField field = new MinesweeperField(data);
		then(field.toString()).isEqualTo("*2\n2*");
	}
	
	@Test @DisplayName("returns field totally filled with mines.")
	void returnsFieldTotallyFilledWithMines() {
		final char[][] data = {
				{'*', '*'},
				{'*', '*'}
		};
		final MinesweeperField field = new MinesweeperField(data);
		then(field.toString()).isEqualTo("**\n**");
	}
	
	@Test @DisplayName("returns zeros for field without mines.")
	void returnsZerosForFieldWithoutMines() {
		final char[][] data = {
				{'.', '.'},
				{'.', '.'}
		};
		final MinesweeperField field = new MinesweeperField(data);
		then(field.toString()).isEqualTo("00\n00");
	}
}
