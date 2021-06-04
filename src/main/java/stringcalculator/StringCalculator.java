package stringcalculator;

import static java.util.Arrays.stream;


public class StringCalculator {

	public int add(final String numbers) {
		if (numbers.isEmpty()) {
			return 0;
		}

		return stream(numbers.split("[,\n]"))
				.mapToInt(Integer::parseInt)
				.sum();
	}
}
