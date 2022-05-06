package vowelcount;

import java.util.Arrays;
import java.util.List;

public class Vowels {

	static List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');

	public static int getCount(final String str) {

		int vowelsCount = 0;
		for (int i = 0; i < str.length(); i++) {
			final char c = str.charAt(i);

			if (vowels.contains(c)) {
				vowelsCount++;
			}
		}
		return vowelsCount;
	}

}