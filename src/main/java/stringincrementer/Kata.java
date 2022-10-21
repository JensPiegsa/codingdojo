package stringincrementer;

import java.math.BigInteger;

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
		final BigInteger i = tail.isEmpty() ? BigInteger.ZERO : new BigInteger(tail);
		final String result = String.valueOf(i.add(BigInteger.ONE));
		final int leadingZeroCount = Math.max(0, tail.length() - result.length());
		return "0".repeat(leadingZeroCount) + result;
	}
}