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

	private boolean matchesWeakly(String[] digit, String[] lines) {
		int errorCount = 0;
		for(int row = 0; row < 3; row++){
			for(int column = 0; column < 3; column++){
				char expectedChar = digit[row].charAt(column);
				char actualChar = lines[row].charAt(column);
				if(expectedChar != actualChar){
					errorCount++;
				}
			}
		}
		return errorCount <= 1;
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
