package bankocr;

import static org.assertj.core.api.BDDAssertions.then;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankOcrTest {

	// room for improvement:
	// TODO move digit test(s) to DigitTest
	// TODO refactoring: extract class AccountNumber
	// TODO move account number tests to AccountNumberTest
	
	private final BankOcr bankOcr = new BankOcr();

	@Test @DisplayName("readNumbers")
	void readNumbers() {
		final Path path = getTestResource("AccountNr.txt");
		final List<AccountNumber> accountNumbers = bankOcr.parse(path);
		then(accountNumbers).asString("123456789","023456789");
	}

	@Test @DisplayName("test all digits")
	void testAllDigits() {

		for(int columnIndex = 0; columnIndex <= 9; columnIndex++) {

			final Digit digit = bankOcr.parseColumnAsDigit(columnIndex,
					" _     _  _     _  _  _  _  _ ",
					"| |  | _| _||_||_ |_   ||_||_|",
					"|_|  ||_  _|  | _||_|  ||_| _|");
			then(digit.getInt()).isEqualTo(columnIndex);
		}
	}

	private Path getTestResource(final String filename) {
		try {
			return Paths.get(BankOcrTest.class.getResource(filename).toURI());
		} catch (final URISyntaxException e) {
			throw new IllegalArgumentException("test resource not found", e);
		}
	}
	
	//account number: 3  4  5  8  8  2  8  6  5
	//position names: d9 d8 d7 d6 d5 d4 d3 d2 d1
	// (1*d1 + 2*d2 + 3*d3 + ... + 9*d9) mod 11 = 0
	
	@Test @DisplayName("checksum for account number is valid")
	void checksumForAccountNumberIsValid() {
		final String accountNumber = "345882865";
		final boolean validAccountNumber = bankOcr.isValidAccountNumber(accountNumber);
		then(validAccountNumber).isTrue();
	}
	
	@Test @DisplayName("checksum for account number is invalid")
	void checksumForAccountNumberIsInvalid() {
		final String accountNumber = "345882866";
		final boolean validAccountNumber = bankOcr.isValidAccountNumber(accountNumber);
		then(validAccountNumber).isFalse();
	}
}
