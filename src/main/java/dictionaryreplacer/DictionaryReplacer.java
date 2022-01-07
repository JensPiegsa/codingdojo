package dictionaryreplacer;

import java.util.Map;
import java.util.Map.Entry;

public class DictionaryReplacer {
	public static String replace(final String input, final Map<String, String> dictionary) {
		
		String interimResult = input;
		for (final var entry : dictionary.entrySet()) {
			final String possibleInput = "$" + entry.getKey() + "$";
			interimResult = input.replace(possibleInput, entry.getValue());
		}

		return interimResult;
	}
}
