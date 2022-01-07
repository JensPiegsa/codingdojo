package dictionaryreplacer;

import java.util.Map;
import java.util.Map.Entry;

public class DictionaryReplacer {
	public static String replace(final String input, final Map<String, String> dictionary) {
		
		String interimResult = input;
		if (!dictionary.isEmpty()) {

			final var entry = dictionary.entrySet().stream().findFirst().get();
//			for (final var entry : dictionary.entrySet()) {
			final String possibleInput = "$" + entry.getKey() + "$";
			interimResult = input.replace(possibleInput, entry.getValue());
//			}
		}

		return interimResult;
	}
}
