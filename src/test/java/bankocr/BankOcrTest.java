package bankocr;

import static org.assertj.core.api.BDDAssertions.then;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankOcrTest {

	@Test @DisplayName("readNumbers")
	void readNumbers() {
		// given
		final Path path = getTestResource("AccountNr.txt");

		// when
		final List<String> accountNumbers = new BankOcr().parse(path);


		// then
		then(accountNumbers).containsExactly("123456789");
	}

	private Path getTestResource(final String filename) {
		try {
			return Paths.get(BankOcrTest.class.getResource(filename).toURI());
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("test resource not found", e);
		}
	}
}
