package bankocr;

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

	public Digit(final String firstLine, final String secondLine, final String thirdLine) {

		for (int i = 0; i < digits.length; i++) {
			final String[] digit = digits[i];
			if (digit[0].equals(firstLine) && digit[1].equals(secondLine) && digit[2].equals(thirdLine)) {
				intValue = i;
			}
		}
	}

	@Deprecated
	public int getInt() {
		return intValue;
	}

	public List<Integer> getPossibleValues() {
		return Collections.singletonList(7);
	}
}
