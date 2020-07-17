package bankocr;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BankOcr {

	public List<String> parse(final Path path) {

		final ArrayList<String> accountNumbers = new ArrayList<>();

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

		final String accountNumber = parseNextAccountNumber(firstLine, secondLine, thirdLine);

		return accountNumber;
	}

	private String parseNextAccountNumber(final String firstLine, final String secondLine, final String thirdLine) {

		String accountNr = "";
		for (int pos = 0; pos < 9; pos++) {
			System.out.println("Pos: " + pos);
			final Digit actualDigit = parseDigit(pos, firstLine, secondLine, thirdLine);
			accountNr = accountNr + actualDigit.getInt();
		}
		return accountNr;
	}

	Digit parseDigit(final int position, final String firstLine, final String secondLine, final String thirdLine) {

		int i = position * 3;
		return new Digit(firstLine.substring(i, i + 3),
				secondLine.substring(i, i + 3),
				thirdLine.substring(i, i + 3));
	}
}
