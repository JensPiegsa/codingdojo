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
		final String secondLine = bufferedReader.readLine();
		final String thirdLine = bufferedReader.readLine();
		bufferedReader.readLine();

		return null;
	}
}
