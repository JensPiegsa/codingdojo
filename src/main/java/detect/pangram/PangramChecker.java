package detect.pangram;

import java.util.Locale;

public class PangramChecker {

	public boolean check(String sentence) {

		final String lowerCase = sentence.toLowerCase(Locale.ENGLISH);
		boolean result = true;

		for (char letter = 'a'; letter <= 'z'; letter++) {
            result &= lowerCase.indexOf(letter) >= 0;
		}
		return result;
	}
}