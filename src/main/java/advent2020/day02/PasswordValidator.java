package advent2020.day02;

import java.util.List;

public class PasswordValidator {
	
	public long countValid(final List<String> passwords) {
		
		return passwords.stream()
				.map(s -> s.split(": *"))
				.filter(a -> isValid(a[0], a[1]))
				.count();
	}

	private boolean isValid(final String policy, final String password) {

		final String[] strings = policy.split(" +");
		final String[] bounds = strings[0].split("-");

		int lowerBound = Integer.parseInt(bounds[0]);
		int upperBound = Integer.parseInt(bounds[1]);
		char policyChar = strings[1].charAt(0);
		
		long countedChars = password.chars()
				.filter(c -> c == policyChar)
				.count();
		
		return countedChars >= lowerBound && countedChars <= upperBound;
	}
	
}
