package binomialexpansion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KataSolution {

	public static String expand(final String expression) {

		final Pattern pattern = Pattern.compile(
				"\\("         // (
				+ "(-?\\d*)"        // a
				+ "(\\w)"           // x
				+ "\\+?"            // +
				+ "(-?\\d*)"        // b
				+ "\\)\\^"          // )^
				+ "(\\d+)");        // n

		final Matcher matcher = pattern.matcher(expression);

		matcher.find();
		
		final String aString = matcher.group(1);
		final String x = matcher.group(2);
		final long a = aString.isEmpty() || "-".equals(aString) ? Long.parseLong(aString + "1") : Long.parseLong(aString);
		final long b = Long.parseLong(matcher.group(3));
		final long n = Long.parseLong(matcher.group(4));

		System.out.println("%s <=> ( %d * %s + (%d) ) ^ %d".formatted(expression, a, x, b, n));

		String result = "";
		if (b == 0L) {
			final long c = power(a, n);
			if (c == -1L) {
				result += "-";
			} else if (c != 1L) {
				result += c;
			} 
			return result + x + "^" + n;
		}
		for (long k = 0L; k <= n; k++) {
			final long c = binomialCoefficient(n, k) * power(a, n - k) * power(b, k);
			String cStr = "";
			if (c == 1L && n - k > 0L) {
				cStr = "";
			} else if (c == -1L && n - k > 0L) {
				cStr = "-";
			} else {
				cStr = String.valueOf(c);
			} 
			result += k == 0L || c < 0L ? cStr : "+" + cStr;

			if (n - k == 1L) {
				result += x;
			} else if (n - k > 1L) {
				result += x + "^" + (n-k);
			}
		}
		return result;
	}

	static long power(final long a, final long b) {
		return (long) Math.pow(a, b);
	}

	static long binomialCoefficient(final long n, final long k) {
		return faculty(n) / (faculty(n - k) * faculty(k));
	}

	static long faculty(final long k) {
		long product = 1L;
		for (long i = 2L; i <= k; i++) {
			product *= i;
		}
		return product;
	}
}