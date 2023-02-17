package stripcomments;

import static java.util.stream.Collectors.*;

import java.util.Arrays;

public class StripComments {

	private StripComments() {
	}

	public static String stripComments(final String text, final String[] commentSymbols) {
		final String[] lines = text.split("\n");
		return Arrays.stream(lines)
				.map(input -> removeComment(input, commentSymbols))
				.map(String::stripTrailing)
				.collect(joining("\n"));
	}

	public static String removeComment(final String line, final String[] commentSymbols) {
		String result = line;
		for (final String symbol : commentSymbols) {
			result = removeComment(result, symbol);
		}
		return result;
	}

	public static String removeComment(final String line, final String commentSymbol) {
		final int commentSymbolIndex = line.indexOf(commentSymbol);
		if (commentSymbolIndex > -1) {
			return line.substring(0, commentSymbolIndex);
		}
		return line;
	}
}
