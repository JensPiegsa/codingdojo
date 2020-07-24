package bankocr;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A Digit")
class DigitTest {

	@Test @DisplayName("test digit zero")
	void testDigitZero() {

		final Digit digit = new Digit(
				" _ ",
				"| |",
				"|_|");
		then(digit.getInt()).isEqualTo(0);
	}

	@Test @DisplayName("test digit one")
	void testDigitOne() {

		final Digit digit = new Digit(
				"   ",
				"  |",
				"  |");
		then(digit.getInt()).isEqualTo(1);
	}

	@Test @DisplayName("test digit two")
	void testDigitTwo() {

		final Digit digit = new Digit(
				" _ ",
				" _|",
				"|_ ");
		then(digit.getInt()).isEqualTo(2);
	}

	@Test @DisplayName("test digit three")
	void testDigitThree() {

		final Digit digit = new Digit(
				" _ ",
				" _|",
				" _|");
		then(digit.getInt()).isEqualTo(3);
	}

	@Test @DisplayName("test digit four")
	void testDigitFour() {

		final Digit digit = new Digit(
				"   ",
				"|_|",
				"  |");
		then(digit.getInt()).isEqualTo(4);
	}

	@Disabled
	@Test @DisplayName("test all digits")
	void testAllDigits() {

		for(int i = 0; i <= 9; i++) {

			final Digit digit = new Digit(
					" _     _  _     _  _  _  _  _ ",
					"| |  | _| _||_||_ |_   ||_||_|",
					"|_|  ||_  _|  | _||_|  ||_| _|");
			then(digit.getInt()).isEqualTo(i);
		}
	}
}