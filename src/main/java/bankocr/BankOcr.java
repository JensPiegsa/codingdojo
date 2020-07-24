package bankocr;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("StringConcatenationInLoop")
public class BankOcr {

	@Deprecated
	public static final int NUMBER_OF_DIGITS = 9;

	public List<AccountNumber> parse(final Path path) {

		final List<AccountNumber> accountNumbers = new ArrayList<>();

		try (final BufferedReader bufferedReader = Files.newBufferedReader(path)) {

			boolean more = true;
			while (more) {
				final AccountNumber accountNumber = parseNextAccountNumber(bufferedReader);
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

	private AccountNumber parseNextAccountNumber(final BufferedReader bufferedReader) throws IOException {
		final String firstLine = bufferedReader.readLine();

		if (firstLine == null) {
			return null;
		}
		final String secondLine = bufferedReader.readLine();
		final String thirdLine = bufferedReader.readLine();
		bufferedReader.readLine();

		return new AccountNumber(firstLine, secondLine, thirdLine);
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
