package advent2020.day04;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PassportValidatorTest {
	
/*		 | part I                | part II
---------|-----------------------|----------------------------------------------------
         | cid (Country ID)      | 
required | byr (Birth Year)      | four digits; at least 1920 and at most 2002
required | iyr (Issue Year)      | four digits; at least 2010 and at most 2020
required | eyr (Expiration Year) | four digits; at least 2020 and at most 2030
required | hcl (Hair Color)      | a # followed by exactly six characters 0-9 or a-f
required | ecl (Eye Color)       | exactly one of: amb blu brn gry grn hzl oth
required | pid (Passport ID)     | a nine-digit number, including leading zeroes
required | hgt (Height)          | a number followed by either cm or in:
         |                       |  -> cm: number must be at least 150 and at most 193
         |                       |  -> in: number must be at least 59 and at most 76
*/
	PassportValidator passportValidator = new PassportValidator();

	@Nested @DisplayName("Part II")
	class PartTwo{

		PassportElementValidator passportElementValidator = new PassportElementValidator();

		@Test @DisplayName("validate birth year")
		void validateBirthYear() {
			// given
			String input = "byr:1937";

			// when
			final boolean result = passportElementValidator.isValid(input);

			// then
			then(result).isTrue();
		}

		@Test @DisplayName("validate issue year")
		void validateIssueYear() {
			// given
			String input = "iyr:2011";

			// when
			final boolean result = passportElementValidator.isValid(input);

			// then
			then(result).isTrue();
		}

		@Test @DisplayName("validate expiration year")
		void validateExpirationYear() {
			// given
			String input = "eyr:2021";

			// when
			final boolean result = passportElementValidator.isValid(input);

			// then
			then(result).isTrue();
		}
		
		@Test @DisplayName("validate hair color")
		void validateHairColor() {
			// given
			String input = "hcl:#123456";

			// when
			final boolean result = passportElementValidator.isValid(input);

			// then
			then(result).isTrue();
		}
		
		@Test @DisplayName("validate eye color")
		void validateEyeColor() {
			// given
			String input = "ecl:amb";

			// when
			final boolean result = passportElementValidator.isValid(input);

			// then
			then(result).isTrue();
		}
		
		@Test @DisplayName("validate passport ID")
		void validatePassportId() {
			// given
			String input = "pid:000123456";

			// when
			final boolean result = passportElementValidator.isValid(input);

			// then
			then(result).isTrue();
		}
		
		@Test @DisplayName("validate height")
		void validateHeight() {
			//given
			String inputOne = "hgt:170cm";
			String inputTwo = "hgt:70in";

			// when
			final boolean resultOne = passportElementValidator.isValid(inputOne);
			final boolean resultTwo = passportElementValidator.isValid(inputTwo);

			// then
			then(resultOne).isTrue();
			then(resultTwo).isTrue();
		}

		@Test @DisplayName("validation test part 2 with input.txt")
		void validationTestWithInputTxt() {
			// given
			String allPassports = contentOf(getClass().getResource("input.txt"));

			// when
			final long numberOfValidPassports = passportValidator.countValidPart2(allPassports);

			// then
			then(numberOfValidPassports).isEqualTo(123);
		}
	}

	@Nested @DisplayName("Part I")
	class PartOne {

		@Test @DisplayName("passport validation")
		void passportValidation() {
			// given
			String validPassport = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm";

			// when
			final boolean result = passportValidator.isValid(validPassport);

			// then
			then(result).isTrue();
		}

		@Test @DisplayName("split passport heap in single passports")
		void splitPassportHeapInSinglePassports() {
			// given
			String passports = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
					"byr:1937 iyr:2017 cid:147 hgt:183cm\n" +
					"\n" +
					"iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
					"hcl:#cfa07d byr:1929";
			// when
			final String[] splitPassports = passportValidator.splitPassports(passports);

			// then
			then(splitPassports)
					.containsExactlyInAnyOrder(
							"ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm",
							"iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\nhcl:#cfa07d byr:1929");
		}

		@Test @DisplayName("first validator test")
		void firstValidatorTest() {
			// given
			String passports = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
					"byr:1937 iyr:2017 cid:147 hgt:183cm\n" +
					"\n" +
					"iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
					"hcl:#cfa07d byr:1929";

			// when
			final long numberOfValidPassports = passportValidator.countValid(passports);

			// then
			then(numberOfValidPassports).isEqualTo(1);
		}

		@Test @DisplayName("validation test with example.txt")
		void validationTestWithExampleTxt() {
			// given
			String allPassports = contentOf(getClass().getResource("example.txt"));

			// when
			final long numberOfValidPassports = passportValidator.countValid(allPassports);

			// then
			then(numberOfValidPassports).isEqualTo(2);
		}

		@Test @DisplayName("validation test with input.txt")
		void validationTestWithInputTxt() {
			// given
			String allPassports = contentOf(getClass().getResource("input.txt"));

			// when
			final long numberOfValidPassports = passportValidator.countValid(allPassports);

			// then
			then(numberOfValidPassports).isEqualTo(206);
		}

	}
}
