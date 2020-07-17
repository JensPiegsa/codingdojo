package bankocr;

import static org.assertj.core.api.BDDAssertions.then;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankOcrTest {

	private final BankOcr bankOcr = new BankOcr();

	@Test @DisplayName("readNumbers")
	void readNumbers() {
		final Path path = getTestResource("AccountNr.txt");
		final List<String> accountNumbers = bankOcr.parse(path);
		then(accountNumbers).containsExactly("123456789","023456789");
	}

	@Test @DisplayName("test digit zero")
	void testDigitZero() {
		
		final Digit digit = bankOcr.parseDigit(0,
				" _ ",
				"| |",
				"|_|");
		then(digit.getInt()).isEqualTo(0);
	}

	@Test @DisplayName("test digit one")
	void testDigitOne() {

		final Digit digit = bankOcr.parseDigit(0,
				"   ",
				"  |",
				"  |");
		then(digit.getInt()).isEqualTo(1);
	}

	@Test @DisplayName("test all digits")
	void testAllDigits() {

		for(int i = 0; i <= 9; i++) {

			final Digit digit = bankOcr.parseDigit(i,
					" _     _  _     _  _  _  _  _ ",
					"| |  | _| _||_||_ |_   ||_||_|",
					"|_|  ||_  _|  | _||_|  ||_| _|");
			then(digit.getInt()).isEqualTo(i);
		}
	}

	private Path getTestResource(final String filename) {
		try {
			return Paths.get(BankOcrTest.class.getResource(filename).toURI());
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("test resource not found", e);
		}
	}
	
	//account number: 3  4  5  8  8  2  8  6  5
	//position names: d9 d8 d7 d6 d5 d4 d3 d2 d1
	// (d1+2*d2+3*d3 +..+9*d9) mod 11 = 0
	@Test @DisplayName("checksum for account number is valid")
	void checksumForAccountNumberIsValid() {
		// given
		String accountNumber = "345882865";
		
		// when
		boolean validAccountNumber = bankOcr.isValidAccountNumber(accountNumber);
		
		// then
		then(validAccountNumber).isTrue();
	}
}
