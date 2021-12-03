package valid.parentheses;

public class Solution {

	public static boolean validParentheses(final String parens) {
		int countOpen = 0;
		for (int i = 0; i < parens.length(); i++) {
			final char currentChar = parens.charAt(i);
			if (currentChar == '(') {
				countOpen++;
			} else if (currentChar == ')') {
				countOpen--;
			}
		}
		return countOpen == 0;
	}
}