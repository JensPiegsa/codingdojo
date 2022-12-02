package frequentwords;

import static java.util.Comparator.reverseOrder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class TopWords {

	private static final Pattern SPLIT_PATTERN = Pattern.compile("[^a-zA-Z']+");
	private static final Pattern MALFORMED_WORD = Pattern.compile("'+");

	public static List<String> top3(final String text) {
		final Map<String, Integer> wordCount = new LinkedHashMap<>();
		for (final String word : SPLIT_PATTERN.split(text)) {
			final String lowerCaseWords = word.toLowerCase();
			wordCount.compute(lowerCaseWords, (w, c) -> nullSafeIncrement(c));
		}
		return wordCount.entrySet().stream()
				.sorted(Entry.comparingByValue(reverseOrder()))
				.map(Entry::getKey)
				.filter(TopWords::isWord)
				.limit(3L)
				.toList();
	}

	private static int nullSafeIncrement(final Integer c) {
		return c == null ? 1 : c + 1;
	}

	private static boolean isWord(final String s) {
		return !s.isEmpty() && !MALFORMED_WORD.matcher(s).matches();
	}

	private TopWords() {
	}
}