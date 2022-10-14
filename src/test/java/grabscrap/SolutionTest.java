package grabscrap;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class SolutionTest {
	
	@Test @DisplayName("return empty list when no given data.")
	void returnEmptyListWhenNoGivenData() {
		final List<String> empty = List.of();
		final List<String> actual = Grab.grabscrab("not found", empty);
		assertThat(actual).isEmpty();
	}
	
	@Test @DisplayName("exact match")
	void exactMatch() {
		final List<String> dictionary = List.of("found");
		final List<String> actual = Grab.grabscrab("found", dictionary);
		assertThat(actual).containsExactly("found");
	}
	
	@Test @DisplayName("scrambled match")
	void scrambledMatch() {
		final List<String> dictionary = List.of("found");
		final List<String> actual = Grab.grabscrab("dofun", dictionary);
		assertThat(actual).containsExactly("found");
	}


	static private String[][][] DATA = {
			//    anagram       words_as_array                       expected_as_array
			{{"trisf"},     {"first"},                             {"first"}},
			{{"oob"},       {"bob", "baobab"},                     {}},
			{{"ainstuomn"}, {"mountains", "hills", "mesa"},        {"mountains"}},
			{{"oolp"},      {"donkey", "pool", "horse", "loop"},   {"pool", "loop"}},
			{{"ortsp"},     {"sport", "parrot", "ports", "matey"}, {"sport", "ports"}},
			{{"ourf"},      {"one","two","three"},                 {}}
	};

	@Test void fixed_tests() {
		for(String[][] data: DATA){
			var anagram = data[0][0];
			var words   = Arrays.stream(data[1]).collect(Collectors.toList());
			var exp     = Arrays.stream(data[2]).collect(Collectors.toList());
			var msg     = String.format("Testing \"%s\" against %s", anagram, words);
			var actual  = Grab.grabscrab(anagram,words);
			assertEquals(exp, actual, msg);
		}
	}
}
