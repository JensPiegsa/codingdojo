package bankocr;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankOcr {

	public List<AccountNumber> parse(final Path path) {

		final List<AccountNumber> accountNumbers = new ArrayList<>();

		try (final BufferedReader bufferedReader = Files.newBufferedReader(path)) {

			boolean more = true;
			while (more) {
				final Optional<AccountNumber> accountNumber = parseNextAccountNumber(bufferedReader);
				more = accountNumber.isPresent();
				if (more) {
					accountNumbers.add(accountNumber.get());
				}
			}

		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
		return accountNumbers;
	}

	private Optional<AccountNumber> parseNextAccountNumber(final BufferedReader bufferedReader) throws IOException {
		final String firstLine = bufferedReader.readLine();

		if (firstLine == null) {
			return Optional.empty();
		}
		final String secondLine = bufferedReader.readLine();
		final String thirdLine = bufferedReader.readLine();
		bufferedReader.readLine();

		return Optional.of(new AccountNumber(firstLine, secondLine, thirdLine));
	}

}
