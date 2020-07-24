package bankocr;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountNumberTest {

	// TODO AccountNumber(...).toString()
	
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
}