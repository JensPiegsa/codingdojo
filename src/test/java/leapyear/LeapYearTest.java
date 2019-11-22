package leapyear;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LeapYearTest {

	@Test @DisplayName("All years divisible by 400 ARE leap years.")
	void allYearsDivisibleBy400AreLeapYears() {
		assertTrue(new LeapYear().isLeapYear(2000));
	}

	@Test @DisplayName("All years divisible by 100 but not by 400 are NOT leap years.")
	void allYearsDivisibleBy100ButNotBy400AreNotLeapYears() {
		assertTrue(new LeapYear().isLeapYear(1700));
		assertTrue(new LeapYear().isLeapYear(1800));
		assertTrue(new LeapYear().isLeapYear(1900));
		assertTrue(new LeapYear().isLeapYear(2100));
	}

	@Test @DisplayName("All years divisible by 4 but not by 100 ARE leap years.")
	void allYearsDivisibleBy4ButNotBy100AreLeapYears() {
		assertTrue(new LeapYear().isLeapYear(2008));
		assertTrue(new LeapYear().isLeapYear(2012));
		assertTrue(new LeapYear().isLeapYear(2016));
	}

	@Test @DisplayName("All years not divisible by 4 are NOT leap years.")
	void allYearsNotDivisibleBy4AreNotLeapYears() {
		assertFalse(new LeapYear().isLeapYear(2017));
		assertFalse(new LeapYear().isLeapYear(2018));
		assertFalse(new LeapYear().isLeapYear(2019));
	}
}
