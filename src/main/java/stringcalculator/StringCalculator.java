package stringcalculator;

import java.util.List;
import java.util.stream.Collectors;

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

		List<Integer> preparedIntNumbers = stream(preparedNumbers.split(defaultDelimiter))
				.map(Integer::parseInt)
				.collect(Collectors.toList());
		raiseExceptionForNegativeNumbers(preparedIntNumbers);
		return preparedIntNumbers.stream()
				.mapToInt(n -> n)
				.sum();
	}

	private void raiseExceptionForNegativeNumbers(List<Integer> preparedIntNumbers) {
		List<String> negativeNumbers = preparedIntNumbers.stream()
				.filter(number -> number < 0)
				.map(String::valueOf)
				.collect(Collectors.toList());
		if (!negativeNumbers.isEmpty()) {
			String error = "negatives not allowed: " + String.join(", ", negativeNumbers);
			throw new IllegalArgumentException(error);
		}
	}

	private boolean hasDelimiterDeclaration(String numbers) {
		return numbers.startsWith("//");
	}
}
