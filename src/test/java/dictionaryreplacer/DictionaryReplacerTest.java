package dictionaryreplacer;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DictionaryReplacerTest {

	/*
	 * Dictionary replacer
	 *
	 * This kata is about making a simple string replacer.
	 * It is inspired by Corey Haines Lightning talk about practicing.
	 * (aac2009.confreaks.com/06-feb-2009-20-30-lightning-talk-under-your-fingers-corey-haines.html)
	 *
	 * Create a method that takes a string and a dictionary,
	 * and replaces every key in the dictionary pre and suffixed with a dollar sign,
	 * with the corresponding value from the Dictionary.
	 *
	 * Tests
	 *
	 * input : “”, dict empty, output:“”
	 *
	 * input : “\$temp\$“, dict [“temp”, “temporary”], output: “temporary”
	 *
	 * input : “\$temp\$ here comes the name \$name\$“, dict [“temp”, “temporary”] [“name”, “John Doe”], output : “temporary here comes the name John Doe”
	 */

	@Test @DisplayName("accepts empty input and dictionary.")
	void acceptsEmptyInputAndDictionary() {
		// given
		final String input = "";
		final Map<String, String> dictionary = Map.of();

		// when
		final String output = DictionaryReplacer.replace(input, dictionary);

		// then
		then(output).isEmpty();
	}
	
	@Test @DisplayName("replaces '$key$' in input with dictionary entry 'value'.")
	void replacesKeyInInputWithDictionaryEntryValue() {
		// given
		final String input = "$key$";
		final Map<String, String> dictionary = Map.of("key", "value");

		// when
		final String output = DictionaryReplacer.replace(input, dictionary);

		// then
		then(output).isEqualTo("value");
	}
}