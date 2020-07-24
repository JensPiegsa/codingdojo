package bankocr;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("StringConcatenationInLoop")
public class BankOcr {

	public static final int NUMBER_OF_DIGITS = 9;
	public static final int DIGIT_WIDTH = 3;

	public List<String> parse(final Path path) {

		final List<String> accountNumbers = new ArrayList<>();

		try (final BufferedReader bufferedReader = Files.newBufferedReader(path)) {

			boolean more = true;
			while (more) {
				final String accountNumber = parseNextAccountNumber(bufferedReader);
				more = accountNumber != null;
				if (more) {
					accountNumbers.add(accountNumber);
				}
			}

		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
		return accountNumbers;
	}

	private String parseNextAccountNumber(final BufferedReader bufferedReader) throws IOException {
		final String firstLine = bufferedReader.readLine();

		if (firstLine == null) {
			return null;
		}
		final String secondLine = bufferedReader.readLine();
		final String thirdLine = bufferedReader.readLine();
		bufferedReader.readLine();

		return parseNextAccountNumber(firstLine, secondLine, thirdLine);
	}

	private String parseNextAccountNumber(final String... lines) {

		String accountNr = "";
		for (int pos = 0; pos < NUMBER_OF_DIGITS; pos++) {
			final Digit actualDigit = parseColumnAsDigit(pos, lines);
			accountNr += actualDigit.getInt();
		}
		return accountNr;
	}

	Digit parseColumnAsDigit(final int columnIndex, final String... lines) {

		final int charIndex = columnIndex * DIGIT_WIDTH;
		return new Digit(lines[0].substring(charIndex, charIndex + DIGIT_WIDTH),
				lines[1].substring(charIndex, charIndex + DIGIT_WIDTH),
				lines[2].substring(charIndex, charIndex + DIGIT_WIDTH));
	}

	public boolean isValidAccountNumber(final String accountNumber) {
		
		int checksum = 0;
		for (int pos = NUMBER_OF_DIGITS - 1; pos >= 0; pos--) {
			final char digitChar = accountNumber.charAt(pos);
			final int digit = Integer.parseInt(String.valueOf(digitChar));
			final int factor = NUMBER_OF_DIGITS - pos;
			checksum += digit * factor;
		}
		return checksum % 11 == 0;
	}
}
