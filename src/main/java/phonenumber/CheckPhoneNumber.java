package phonenumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class CheckPhoneNumber {

	public boolean isConsistent(final Map<String, String> phoneNumbers) {

		Objects.requireNonNull(phoneNumbers);

		if (phoneNumbers.isEmpty()) {
			return true;
		}

		final List<String> formatedNumbers = phoneNumbers.values()
				.stream().map(this::formatNumber)
				.collect(Collectors.toList());

		return !formatedNumbers.stream()
				.anyMatch(formatedNumber -> checkExistsAsPrefix(formatedNumber, new ArrayList<>(formatedNumbers)));
	}

	private boolean checkExistsAsPrefix(final String formatedNumber, final List<String> formatedNumbers) {
		formatedNumbers.remove(formatedNumber);
		return formatedNumbers.stream()
				.anyMatch(currentNumber -> currentNumber.startsWith(formatedNumber));
	}

	private String formatNumber(final String number) {
		return number.replace(" ", "");
	}

}
