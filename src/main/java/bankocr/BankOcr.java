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
			final Digit actualDigit = parseDigit(pos, lines);
			accountNr += actualDigit.getInt();
		}
		return accountNr;
	}

	Digit parseDigit(final int position, final String... lines) {

		int i = position * DIGIT_WIDTH;
		return new Digit(lines[0].substring(i, i + DIGIT_WIDTH),
				lines[1].substring(i, i + DIGIT_WIDTH),
				lines[2].substring(i, i + DIGIT_WIDTH));
	}

	public boolean isValidAccountNumber(final String accountNumber) {
		
		for (int pos = 0; pos < NUMBER_OF_DIGITS; pos++) {
			
		}
		int number = Integer.parseInt(accountNumber);
		return true;
	}
}
