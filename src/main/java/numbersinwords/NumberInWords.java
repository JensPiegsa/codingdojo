package numbersinwords;

public class NumberInWords {

	private static final String[] digitWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	private static final String[] teenWords = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	private static final String[] tensWords = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};


	public String toWords(final int number) {

		if (number >= 0 && number < 10) {
			return digitWords[number];
		} else if (number >= 10 && number < 20) {
			return teenWords[number - 10];
		} else if (number >= 20 && number < 100) {
			final int tensWordsIndex = getTens(number) - 2;
			String words = tensWords[tensWordsIndex];

			if (number % 10 != 0) {
				words += " " + toWords(number % 10);
			}
			return words;
		}
		throw new IllegalArgumentException("Number not supported: " + number);
	}

	int getTens(final int givenNumber) {
		return givenNumber / 10;
	}
}
