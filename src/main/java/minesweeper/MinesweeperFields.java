package minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MinesweeperFields {
	
	public String printNumbers(final String input) {

		final String[] inputLines = input.split("\n");

		final List<String> fieldDescriptions = new ArrayList<>();
		int fieldNumber = 1;
		int nextLine = 0;
		while (hasMoreFields(inputLines, nextLine)) {
			
			final String[] dimensions = inputLines[nextLine].split(" ");
			final int rows = Integer.parseInt(dimensions[0]);
			final int columns = Integer.parseInt(dimensions[1]);

			final String fieldDescription = describeField(inputLines, rows, columns, fieldNumber, nextLine + 1);
			fieldDescriptions.add(fieldDescription);
			
			nextLine += 1 + rows;
			fieldNumber++;
			
		}
		return String.join("\n\n", fieldDescriptions);
	}

	private boolean hasMoreFields(final String[] inputLines, final int nextLine) {
		return !"0 0".equals(inputLines[nextLine]);
	}

	private String describeField(final String[] inputLines, 
	                             final int rows, 
	                             final int columns, 
	                             final int fieldNumber, 
	                             final int firstRow) {
		
		final char[][] fieldData = new char[rows][columns];

		for (int row = 0; row < rows; row++) {
			final String rowContent = inputLines[row + firstRow];
			fieldData[row] = rowContent.toCharArray();
		}

		final MinesweeperField minesweeperField = new MinesweeperField(fieldData);

		final String header = "Field #" + fieldNumber + ":\n";

		final String fieldDescription = header + minesweeperField;
		return fieldDescription;
	}
}
