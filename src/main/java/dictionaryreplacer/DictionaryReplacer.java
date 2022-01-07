package dictionaryreplacer;

import java.util.Map;
import java.util.Map.Entry;

public class DictionaryReplacer {
	public static String replace(final String input, final Map<String, String> dictionary) {
		
		String interimResult = input;
		// O(1) can also be replaced by O(n):
		for (final Entry<String, String> entry : dictionary.entrySet()) {
			final String possibleInput = "$" + entry.getKey() + "$";
			interimResult = input.replace(possibleInput, entry.getValue());
		}

		return interimResult;
	}
}
