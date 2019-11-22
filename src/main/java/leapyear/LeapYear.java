package leapyear;

public class LeapYear {
	public boolean isLeapYear(final int year) {
		final boolean ruleOne = year % 400 == 0;
		final boolean ruleTwo = year % 100 == 0 && year % 400 != 0;
		final boolean ruleThree = year % 4 == 0 && year % 100 != 0;
		return ruleOne | ruleTwo | ruleThree;
	}
}
