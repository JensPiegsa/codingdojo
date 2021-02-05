package advent2020.day04;

import java.util.Arrays;
import java.util.List;

public class PassportElementValidator {

	private static final int REQUIRED_NUMBER_OF_DIGITS = 4;
	private static final int BIRTH_YEAR_MIN = 1920;
	private static final int BIRTH_YEAR_MAX = 2002;
	private static final int ISSUE_YEAR_MIN = 2010;
	private static final int ISSUE_YEAR_MAX = 2020;
	private static final int EXPIRATION_YEAR_MIN = 2020;
	private static final int EXPIRATION_YEAR_MAX = 2030;
	private static final int HEIGHT_CM_MIN = 150;
	private static final int HEIGHT_CM_MAX = 193;
	private static final int HEIGHT_INCH_MIN = 59;
	private static final int HEIGHT_INCH_MAX = 76;
	private static final List<String> eyeColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

	public boolean isValid(String keyValueInput) {
		final String[] keyValue = keyValueInput.split(":");
		String key = keyValue[0];
		String value = keyValue[1];
		
		switch (key) {
			case "byr" : return isBirthYearValid(value);
			case "iyr" : return isIssueYearValid(value);
			case "eyr" : return isExpirationYearValid(value);
			case "hcl" : return isHairColorValid(value);
			case "ecl" : return isEyeColorValid(value);
			case "pid" : return isPassporIdValid(value);
			case "hgt" : return isHeightValid(value);
			default : return printUnknownElementAndReturnTrue(key, value);
		}
	}

	private boolean isBirthYearValid(final String value) { 
		final int givenYear = checkLengthParseIntOrReturnZero(value);
		return givenYear >= BIRTH_YEAR_MIN && givenYear <= BIRTH_YEAR_MAX;
	}

	private boolean isIssueYearValid(final String value) {
		final int givenYear = checkLengthParseIntOrReturnZero(value);
		return givenYear >= ISSUE_YEAR_MIN && givenYear <= ISSUE_YEAR_MAX;
	}

	private boolean isExpirationYearValid(final String value) {
		final int givenYear = checkLengthParseIntOrReturnZero(value);
		return givenYear >= EXPIRATION_YEAR_MIN && givenYear <= EXPIRATION_YEAR_MAX;
	}

	private int checkLengthParseIntOrReturnZero(final String number) {
		if (number.length() == REQUIRED_NUMBER_OF_DIGITS) {
			try {
				final int parsedInt = Integer.parseInt(number);
				return parsedInt;
			} catch (NumberFormatException nfe) {
				return 0;
			}
		}
		return 0;
	}

	private boolean isHairColorValid(final String value) {
		return value.matches("#[0-9a-f]{6}");
	}

	private boolean isEyeColorValid(final String value) {
		return eyeColors.contains(value);
	}

	private boolean isPassporIdValid(final String value) {
		return value.matches("[0-9]{9}");
	}

	private boolean isHeightValid(final String value) {

		final int height;
		try {
			height = Integer.parseInt(value.substring(0, value.length() - 2));
		} catch (NumberFormatException nfe) {
			return false;
		}
		
		if (value.endsWith("cm")) {
			return height >= HEIGHT_CM_MIN && height <= HEIGHT_CM_MAX;
		} else if (value.endsWith("in")) {
			return height >= HEIGHT_INCH_MIN && height <= HEIGHT_INCH_MAX;
		}
		return false;
	}

	private boolean printUnknownElementAndReturnTrue(final String key, final String value) {
		System.out.println("Unknown passport element: " + key + " with value: " + value);
		return true;
	}
}
