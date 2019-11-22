package romannumerals;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RomanNumerals {

	public String toRomanNumber(final long arabicNumber) {

		long temporaryNumber = arabicNumber;
		List<Integer> modulos = Arrays.asList(1000, 500, 100, 50, 10, 5, 1);
		Map<Integer, Character> romanMapping = new LinkedHashMap<>();
		romanMapping.put(1000, 'M');
		romanMapping.put(500, 'D');
		romanMapping.put(100, 'C');
		romanMapping.put(50, 'L');
		romanMapping.put(10, 'X');
		romanMapping.put(5, 'V');
		romanMapping.put(1, 'I');


		StringBuilder builder = new StringBuilder();

		for (int actualModulo:modulos) {
			long mod = temporaryNumber % actualModulo;
			long div = temporaryNumber / actualModulo;

			if (div >= 0) {
				temporaryNumber = mod;

				for (int d = 0; d < div; d++) {
					builder.append(romanMapping.get(actualModulo));
				}
			}
		}

		return builder.toString()
				.replace("VIIII","IX")
				.replace("IIII","IV")
				.replace("LXXXX","XC")
				.replace("XXXX","XL")
				.replace("DCCCC","CM")
				.replace("CCCC","CD");
	}
}
