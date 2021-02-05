package advent2020.day04;

import java.util.Arrays;
import java.util.List;

public class PassportValidator {
	
	public long countValid(final String passports) {

		return Arrays.stream(splitPassports(passports))
				.filter(this::isValid)
				.count();
		
	}

	String[] splitPassports(final String passports) {
		return passports.split("\n\n");
	}

	public boolean isValid(final String passport) {
		final List<String> requiredAttributeNames = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
		final String[] passportEntries = passport.split("[ \n]");
		
		for (String requiredAttributeName : requiredAttributeNames) {
			final boolean present = Arrays.stream(passportEntries)
					.anyMatch(entry -> entry.startsWith(requiredAttributeName + ":"));
			if (!present) {
				return false;
			}
		}
		return true;
	}

	public long countValidPart2(final String passports) {

		
		return Arrays.stream(splitPassports(passports))
				.filter(this::isValid2)
				.count();

	}

	public boolean isValid2(final String passport) {
		PassportElementValidator passportElementValidator = new PassportElementValidator();
		final List<String> requiredAttributeNames = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
		final String[] passportEntries = passport.split("[ \n]");

		for (String requiredAttributeName : requiredAttributeNames) {
			final boolean present = Arrays.stream(passportEntries)
					.anyMatch(entry -> entry.startsWith(requiredAttributeName + ":") 
							&& passportElementValidator.isValid(entry));
			if (!present) {
				return false;
			}
		}
		return true;
	}
}
