package bankocr;

public class AccountNumber {
	
	static final int NUMBER_OF_DIGITS = 9;
	static final int DIGIT_WIDTH = 3;
	
	private final String accountNumberString;

	public AccountNumber(final String firstLine, final String secondLine, final String thirdLine) {
		accountNumberString = parseNextAccountNumber(firstLine, secondLine, thirdLine);
	}

	private String parseNextAccountNumber(final String... lines) {

		String accountNr = "";
		for (int pos = 0; pos < NUMBER_OF_DIGITS; pos++) {
			final Digit actualDigit = parseColumnAsDigit(pos, lines);
			accountNr += actualDigit.getInt();
		}
		return accountNr;
	}

	static Digit parseColumnAsDigit(final int columnIndex, final String... lines) {

		final int charIndex = columnIndex * DIGIT_WIDTH;
		return new Digit(lines[0].substring(charIndex, charIndex + DIGIT_WIDTH),
				lines[1].substring(charIndex, charIndex + DIGIT_WIDTH),
				lines[2].substring(charIndex, charIndex + DIGIT_WIDTH));
	}
}
