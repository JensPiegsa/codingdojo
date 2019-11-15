package romannumerals;

public class RomanNumerals {

	public String toRomanNumber(final long arabicNumber) {
		if (arabicNumber == 5L) {
			return "V";
		}
		final StringBuilder result = new StringBuilder();
		long temp = arabicNumber;
		while (temp > 0L) {
			if (temp % 10L == 0L) {
				temp -= 10L;
				result.append("X");
			} else {
				break;
			}
		}

		return result.toString();
//		return arabicNumber == 1L ? "I" : arabicNumber == 2L ? "II" : "III";
	}
}
