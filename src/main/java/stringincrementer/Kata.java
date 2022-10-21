package stringincrementer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kata {
	
	public static String incrementString(final String string) {
		final Pattern pattern = Pattern.compile("^([^0-9]*)([0-9]*)$");
		final Matcher matcher = pattern.matcher(string);
		matcher.matches();
		final String head = matcher.group(1);
		final String tail = matcher.group(2);
		return head + increment(tail);
	}

	private static String increment(final String tail) {
		final int i = tail.isEmpty() ? 0 : Integer.parseInt(tail);
		final String result = String.valueOf(i + 1);
		final int leadingZeroCount = Math.max(0, tail.length() - result.length());
		return "0".repeat(leadingZeroCount) + result;
	}
}