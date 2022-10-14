package grabscrap;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;


public class Grab {

	public static List<String> grabscrab(final String s, final List<String> words) {
		final Word word = new Word(s);
		final PirateDictionary pirateDictionary = new PirateDictionary(words);

		return pirateDictionary.findMatches(word)
				.stream()
				.map(Word::toString)
				.toList();
	}

	private record Word(String word) {

		public boolean matches(final Word other) {
			final Word otherSorted = other.sorted();
			return sorted().matchesExactly(otherSorted);
		}

		private Word sorted() {
			final IntFunction<String> mapper = i -> Character.toString((char) i);
			final String content = word.chars()
					.sorted()
					.mapToObj(mapper)
					.collect(joining());
			return new Word(content);
		}

		private boolean matchesExactly(final Word other) {
			final String otherWord = other.word;
			return word.equals(otherWord);
		}

		@Override
		public String toString() {
			return word;
		}
	}

	private static class PirateDictionary {

		private final List<Word> words;

		PirateDictionary(final List<String> allWords) {
			words = allWords.stream()
					.map(Word::new)
					.toList();
		}

		public List<Word> findMatches(final Word word) {
			final Predicate<Word> matcher = w -> w.matches(word);
			return words.stream()
					.filter(matcher)
					.toList();
		}
	}
}