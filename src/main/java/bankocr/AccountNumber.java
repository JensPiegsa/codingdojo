package bankocr;

public class AccountNumber {

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

}
