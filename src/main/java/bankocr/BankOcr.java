package bankocr;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BankOcr {

	private final Path logFile;

	public BankOcr(final Path logFile) {
		this.logFile = logFile;
	}

	public List<AccountNumber> parse(final Path path) {

		final List<AccountNumber> accountNumbers = new ArrayList<>();

		try (final BufferedReader bufferedReader = Files.newBufferedReader(path)) {

			boolean more = true;
			while (more) {
				final Optional<AccountNumber> optionalAccountNumber = parseNextAccountNumber(bufferedReader);
				more = optionalAccountNumber.isPresent();
				if (more) {
					final AccountNumber accountNumber = optionalAccountNumber.get();
					accountNumbers.add(accountNumber);
					logToFile(accountNumber);
				}
			}

		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}
		return accountNumbers;
	}

	private void logToFile(final AccountNumber accountNumber) {
		try {
			final String logLine = accountNumber.debug() + "\n";
			Files.write(logFile, logLine.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
