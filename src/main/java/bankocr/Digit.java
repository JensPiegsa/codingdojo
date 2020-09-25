package bankocr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Digit {

	private static final String[][] digits = {
			{" _ ",
			 "| |",
			 "|_|"},
			
			{"   ",
			 "  |",
			 "  |"},

			{" _ ",
			 " _|",
			 "|_ "},

			{" _ ",
			 " _|",
			 " _|"},

			{"   ",
			 "|_|",
			 "  |"},

			{" _ ",
			 "|_ ",
			 " _|"},

			{" _ ",
			 "|_ ",
			 "|_|"},

			{" _ ",
			 "  |",
			 "  |"},

			{" _ ",
			 "|_|",
			 "|_|"},

			{" _ ",
			 "|_|",
			 " _|"}
	};
	
	private static final int MAXIMUM_ERROR = 1;

	private int intValue = -1;
	private final ArrayList<Integer> possibleValues;

	public Digit(final String... lines){
		possibleValues = new ArrayList<>();
		for (int i = 0; i < digits.length; i++) {
			final String[] digit = digits[i];
			if (matchesWeakly(digit, lines)){
				possibleValues.add(i);
			}
			if (matchesPerfectly(digit, lines)) {
				intValue = i;
			}
		}
	}

	/**
	 * compare two digits character-wise and allowing one error at maximum
	 */
	private boolean matchesWeakly(String[] expectedDigit, String[] actualDigit) {
		int errorCount = 0;
		for(int row = 0; row < 3; row++){
			for(int column = 0; column < 3; column++){
				char expectedChar = expectedDigit[row].charAt(column);
				char actualChar = actualDigit[row].charAt(column);
				if(expectedChar != actualChar){
					errorCount++;
				}
			}
		}
		return errorCount <= MAXIMUM_ERROR;
	}

	private boolean matchesPerfectly(String[] digit, String[] lines) {
		return digit[0].equals(lines[0]) && digit[1].equals(lines[1]) && digit[2].equals(lines[2]);
	}
	
	@Deprecated
	public int getInt() {
		return intValue;
	}

	public List<Integer> getPossibleValues() {
		return possibleValues;
	}
}
