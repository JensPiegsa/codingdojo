package frequentwords;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("A TopWords")
class TopWordsTest {

	@Nested @DisplayName("unit tests")
	class UnitTests {
		
		@Test @DisplayName("test")
		void test() {
			final String text = " , ; +";
			final var words = TopWords.top3(text);
			then(words).isEmpty();
		}
	}
	
	@Nested @DisplayName("acceptance")
	class Acceptance {
		
		@Test
		public void sampleTests() {
			assertEquals(Arrays.asList("e", "d", "a"),    TopWords.top3("a a a  b  c c  d d d d  e e e e e"));
			assertEquals(Arrays.asList("e", "ddd", "aa"), TopWords.top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"));
			assertEquals(Arrays.asList("won't", "wont"),  TopWords.top3("  //wont won't won't "));
			assertEquals(Arrays.asList("e"),              TopWords.top3("  , e   .. "));
			assertEquals(Arrays.asList(),                 TopWords.top3("  ...  "));
			assertEquals(Arrays.asList(),                 TopWords.top3("  '  "));
			assertEquals(Arrays.asList(),                 TopWords.top3("  '''  "));
			assertEquals(Arrays.asList("a", "of", "on"),  TopWords.top3(Stream
					.of("In a village of La Mancha, the name of which I have no desire to call to",
							"mind, there lived not long since one of those gentlemen that keep a lance",
							"in the lance-rack, an old buckler, a lean hack, and a greyhound for",
							"coursing. An olla of rather more beef than mutton, a salad on most",
							"nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra",
							"on Sundays, made away with three-quarters of his income.")
					.collect(Collectors.joining("\n")) ));
		}
	}
}