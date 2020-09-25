package bankocr;

import static org.assertj.core.api.BDDAssertions.then;

import java.nio.file.Path;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountNumberTest {

	@Test
	@DisplayName("test all digits")
	void testAllDigits() {

		for(int columnIndex = 0; columnIndex <= 9; columnIndex++) {

			final Digit digit = AccountNumber.parseColumnAsDigit(columnIndex,
					" _     _  _     _  _  _  _  _ ",
					"| |  | _| _||_||_ |_   ||_||_|",
					"|_|  ||_  _|  | _||_|  ||_| _|");
			then(digit.getInt()).isEqualTo(columnIndex);
		}
	}

	//account number: 3  4  5  8  8  2  8  6  5
	//position names: d9 d8 d7 d6 d5 d4 d3 d2 d1
	// (1*d1 + 2*d2 + 3*d3 + ... + 9*d9) mod 11 = 0

	@Test @DisplayName("checksum for account number is valid")
	void checksumForAccountNumberIsValid() {
		final AccountNumber accountNumber = new AccountNumber("345882865");
		final boolean validAccountNumber = accountNumber.isChecksumValid();
		then(validAccountNumber).isTrue();
	}

	@Test @DisplayName("checksum for account number is invalid")
	void checksumForAccountNumberIsInvalid() {
		final AccountNumber accountNumber = new AccountNumber("345882866");
		final boolean validAccountNumber = accountNumber.isChecksumValid();
		then(validAccountNumber).isFalse();
	}
	
	@Test @DisplayName("check account number length")
	void checkAccountNumberLength() {
		// given
		String lineOne = " _     _  _     _  _  _  _  _ ";
		String lineTwo = "| |  | _|  ||_||_ |_   ||_||_|";
		String lineThr = "|_|  ||_  _|  | _||_|  ||_| _|";
		
		// when
		Assertions.assertThrows(IllegalArgumentException.class, () -> new AccountNumber(lineOne, lineTwo, lineThr));
	}
	
	@Test @DisplayName("test")
	void test() {
		// given
		String lineOne = "    _  _     _  _  _  _  _ ";
		String lineTwo = "  | _|  ||_||_ |_   ||_||_|";
		String lineThr = "  ||_  _|  | _||_|  ||_| _|";
		
		final AccountNumber accountNumber = new AccountNumber(lineOne, lineTwo, lineThr);
		
		// when
		final String actual = accountNumber.toString();

		// then
		then(actual).isEqualTo("123456789");
	}
}