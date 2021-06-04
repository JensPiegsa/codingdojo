package stringcalculator;

import static java.util.Arrays.stream;


public class StringCalculator {

	public int add(final String numbers) {
		if (numbers.isEmpty()) {
			return 0;
		}

		String defaultDelimiter = "[,\n]";
		String preparedNumbers = numbers;

		if (hasDelimiterDeclaration(numbers)) {
			final int firstNewline = numbers.indexOf("\n");
			defaultDelimiter = numbers.substring(2, firstNewline);
			preparedNumbers = numbers.substring(firstNewline + 1);
		}

		return stream(preparedNumbers.split(defaultDelimiter))
				.mapToInt(Integer::parseInt)
				.sum();
	}

	private boolean hasDelimiterDeclaration(String numbers) {
		return numbers.startsWith("//");
	}
}
