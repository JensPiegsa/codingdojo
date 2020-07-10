package bankocr;

import static org.assertj.core.api.BDDAssertions.then;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankOcrTest {

	// when
	private BankOcr bankOcr = new BankOcr();

	@Test @DisplayName("readNumbers")
	void readNumbers() {
		// given
		final Path path = getTestResource("AccountNr.txt");

		final List<String> accountNumbers = bankOcr.parse(path);


		// then
		then(accountNumbers).containsExactly("123456789","023456789");
	}

	@Test @DisplayName("test digit")
	void testDigit() {
		
		final Digit digit = bankOcr.parseDigit(0,
				" _ ",
				"| |",
				"|_|");
		then(digit.getInt()).isEqualTo(0);
	}


	private Path getTestResource(final String filename) {
		try {
			return Paths.get(BankOcrTest.class.getResource(filename).toURI());
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("test resource not found", e);
		}
	}
}
