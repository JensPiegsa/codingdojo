package stripcommands;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StripComments {

	public static String stripComments(String text, String[] commentSymbols) {

		if(commentSymbols.length == 0)
			throw new IllegalArgumentException("We need at least one symbol for comments");

		final String[] lines = text.split("\n");

		return Arrays.stream(lines)
				.map(line -> stripLine(line, commentSymbols))
				.collect(Collectors.joining("\n"));
	}

	private static String stripLine(String text, String[] commentSymbols) {
		final String symbolSplitter = "[" + String.join("", commentSymbols) + "]";
		final String[] parts = text.split(symbolSplitter);

		final String codeWithoutComments = parts[0];
		return codeWithoutComments.stripTrailing();
	}

}
