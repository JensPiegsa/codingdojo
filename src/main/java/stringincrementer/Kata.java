package stringincrementer;

public class Kata {
	
	public static String incrementString(final String string) {
		int i;
		for (i = string.length() - 1; i >= 0; i--) {
			final char c = string.charAt(i);
			if (!Character.isDigit(c)) {
				break;
			}
		}
		
		final String head = string.substring(0, i + 1);
		final String tail = string.substring(i + 1);
		return head + increment(tail);
	}

	private static String increment(final String tail) {
		final int i = tail.isEmpty() ? 0 : Integer.parseInt(tail);
		final String result = String.valueOf(i + 1);
		final int leadingZeroCount = Math.max(0, tail.length() - result.length());
		return "0".repeat(leadingZeroCount) + result;
	}
}