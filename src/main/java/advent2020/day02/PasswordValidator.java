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

	public long countValid2(final List<String> passwords) {

		return passwords.stream()
				.map(s -> s.split(": *"))
				.filter(a -> isValid2(a[0], a[1]))
				.count();
	}
	
	public boolean isValid2(final String policy, final String password) {

		final String[] strings = policy.split(" +");
		final String[] positions = strings[0].split("-");

		int firstPosition = Integer.parseInt(positions[0]) - 1;
		int secondPosition = Integer.parseInt(positions[1]) - 1;
		char policyChar = strings[1].charAt(0);
		
		return (password.charAt(firstPosition) == policyChar 
				&& password.charAt(secondPosition) != policyChar) ||
				(password.charAt(secondPosition) == policyChar
				&& password.charAt(firstPosition) != policyChar);
	}
}
