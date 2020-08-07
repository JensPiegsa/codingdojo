package bankocr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

public class BankOcrTest {

	@TempDir Path logDirectory;

	@Test @DisplayName("readNumbers")
	void readNumbers() {
		final Path path = getTestResource("AccountNr.txt");
		final BankOcr bankOcr = new BankOcr(logDirectory.resolve("bankOcr.log"));
		final List<AccountNumber> accountNumbers = bankOcr.parse(path);
		then(accountNumbers).extracting(AccountNumber::toString).containsExactly("123456789","023456789");
	}

	private Path getTestResource(final String filename) {
		try {
			return Paths.get(BankOcrTest.class.getResource(filename).toURI());
		} catch (final URISyntaxException e) {
			throw new IllegalArgumentException("test resource not found", e);
		}
	}

	@Test @DisplayName("write account numbers with comment to logfile")
	void writeAccountNumbersWithCommentToLogfile() {
		// given
		final Path path = getTestResource("ValidAndInvalidAccountNr.txt");

		// when
		final Path logFile = logDirectory.resolve("bankOcr.log");
		final BankOcr bankOcr = new BankOcr(logFile);
		final List<AccountNumber> accountNumbers = bankOcr.parse(path);
		
		// then
		then(contentOf(logFile.toFile())).isEqualTo(
				"123456789\n" +
				"023456780 ERR\n" +
				"0??456780 ILL\n");
	}
	
	
}
