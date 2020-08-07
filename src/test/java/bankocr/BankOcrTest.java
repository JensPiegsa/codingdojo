package bankocr;

import static org.assertj.core.api.BDDAssertions.then;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class BankOcrTest {

	private final BankOcr bankOcr = new BankOcr();
	
	@TempDir Path logDirectory;

	@Test @DisplayName("readNumbers")
	void readNumbers() {
		final Path path = getTestResource("AccountNr.txt");
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
		final List<AccountNumber> accountNumbers = bankOcr.parse(path);
		
		// then
	}
}
