package minesweeper;

import static java.util.stream.Collectors.*;

import java.util.Arrays;

public class MinesweeperField {

	private final char[][] fieldData;

	/** first dimension: rows, second dimension: columns */
	public MinesweeperField(final char[][] fieldData) {
		this.fieldData = fieldData.clone();
		calculateNumbers();
	}

	@SuppressWarnings("ImplicitNumericConversion")
	private void calculateNumbers() {
		
		final int rows = fieldData.length;
		for (int row = 0; row < rows; row++) {
			final int columns = fieldData[row].length;
			for (int column = 0; column < columns; column++) {
				if (fieldData[row][column] == '.') {
					final int count = count(row, column);
					fieldData[row][column] = Character.forDigit(count, 10);
				}
			}
		}
	}

	private int count(final int row, final int column) {
		
		int count = 0;
		for (int r = row - 1; r <= row + 1; r++) {
			for (int c = column - 1; c <= column + 1; c++) {
				if (hasMine(r, c)) {
					count++;
				}
			}
		}
		return count;
	}

	@SuppressWarnings("ImplicitNumericConversion")
	private boolean hasMine(final int row, final int column) {

		return isInBounds(row, column) 
				&& fieldData[row][column] == '*';
	}

	private boolean isInBounds(final int row, final int column) {
		return row >= 0 && row < fieldData.length && column >= 0 && column < fieldData[row].length;
	}

	@Override
	public String toString() {
		return Arrays.stream(fieldData)
				.map(String::new)
				.collect(joining("\n"));
	}
}
