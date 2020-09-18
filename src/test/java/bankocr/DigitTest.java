package bankocr;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@DisplayName("A Digit")
@TestMethodOrder(OrderAnnotation.class)
class DigitTest {
	
	@Test @DisplayName("test broken digit") @Order(0)
	void testBrokenDigit() {

		final Digit digit = new Digit(
				" _ ",
				"  |",
				" _|");
		then(digit.getInt()).isEqualTo(-1);
	}

	@Test @DisplayName("test digit zero") @Order(1)
	void testDigitZero() {

		final Digit digit = new Digit(
				" _ ",
				"| |",
				"|_|");
		then(digit.getInt()).isEqualTo(0);
	}

	@Test @DisplayName("test digit one") @Order(2)
	void testDigitOne() {

		final Digit digit = new Digit(
				"   ",
				"  |",
				"  |");
		then(digit.getInt()).isEqualTo(1); 
	}

	@Test @DisplayName("test digit two") @Order(3)
	void testDigitTwo() {

		final Digit digit = new Digit(
				" _ ",
				" _|",
				"|_ ");
		then(digit.getInt()).isEqualTo(2);
	}

	@Test @DisplayName("test digit three") @Order(4)
	void testDigitThree() {

		final Digit digit = new Digit(
				" _ ",
				" _|",
				" _|");
		then(digit.getInt()).isEqualTo(3);
	}

	@Test @DisplayName("test digit four") @Order(5)
	void testDigitFour() {

		final Digit digit = new Digit(
				"   ",
				"|_|",
				"  |");
		then(digit.getInt()).isEqualTo(4);
	}

	@Test @DisplayName("test digit five") @Order(6)
	void testDigitFive() {

		final Digit digit = new Digit(
				" _ ",
				"|_ ",
				" _|");
		then(digit.getInt()).isEqualTo(5);
	}

	@Test @DisplayName("test digit six") @Order(7)
	void testDigitSix() {

		final Digit digit = new Digit(
				" _ ",
				"|_ ",
				"|_|");
		then(digit.getInt()).isEqualTo(6);
	}

	@Test @DisplayName("test digit seven") @Order(8)
	void testDigitSeven() {

		final Digit digit = new Digit(
				" _ ",
				"  |",
				"  |");
		then(digit.getInt()).isEqualTo(7);
	}

	@Test @DisplayName("test digit eight") @Order(9)
	void testDigitEight() {

		final Digit digit = new Digit(
				" _ ",
				"|_|",
				"|_|");
		then(digit.getInt()).isEqualTo(8);
	}

	@Test @DisplayName("test digit nine") @Order(10)
	void testDigitNine() {

		final Digit digit = new Digit(
				" _ ",
				"|_|",
				" _|");
		then(digit.getInt()).isEqualTo(9);
	}


	@Test @DisplayName("test broken digit may be a 7.") @Order(11)
	void testBrokenDigitMayBeASeven() {

		final Digit digit = new Digit(
				" _ ",
				"  |",
				" _|");
		then(digit.getPossibleValues()).contains(7);
	}
	
}