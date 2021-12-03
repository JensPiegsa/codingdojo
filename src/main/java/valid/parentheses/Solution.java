package valid.parentheses;

import java.util.Map;

public class Solution {

	private static final Map<Character, Integer> parities = Map.of('(',1,')',-1);

	public static boolean validParentheses(final String parens) {
		int countOpen = 0;
		for (int i = 0; i < parens.length(); i++) {
			final char currentChar = parens.charAt(i);
			countOpen += parities.getOrDefault(currentChar, 0);
			if (countOpen < 0) {
				return false;
			}
		}
		return countOpen == 0;
	}
}