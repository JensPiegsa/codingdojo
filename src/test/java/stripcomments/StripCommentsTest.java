package stripcomments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class StripCommentsTest {

	@Nested @DisplayName("stripComments()")
	class StripCommentsMethod {
		
		@Test @DisplayName("stripComments() accepts empty string.")
		void stripCommentsAcceptsEmptyString() {
			assertThat(StripComments.stripComments("", new String[]{"#"})).isEmpty();
		}
		
		@Test @DisplayName("stripComments() accepts single word.")
		void stripCommentsAcceptsSingleWord() {
			assertThat(StripComments.stripComments("word", new String[]{"#"})).isEqualTo("word");
		}
		
		@Test @DisplayName("stripComments() strips trailing spaces.")
		void stripCommentsStripsTrailingSpaces() {
			assertThat(StripComments.stripComments("word  ", new String[]{"#"})).isEqualTo("word");
		}
	
		@Test @DisplayName("stripComments() keeps leading spaces.")
		void stripCommentsKeepsLeadingSpaces() {
			assertThat(StripComments.stripComments("  word", new String[]{"#"})).isEqualTo("  word");
		}
	
		@Test @DisplayName("stripComments() strips trailing spaces for multiple lines.")
		void stripCommentsStripsTrailingSpacesForMultipleLines() {
			assertThat(StripComments.stripComments("two \n words", new String[]{"#"})).isEqualTo("two\n words");
		}
	
		@Test @DisplayName("stripComments() keeps empty lines.")
		void stripCommentsKeepsEmptyLines() {
			assertThat(StripComments.stripComments("three\n\nlines", new String[]{"#"})).isEqualTo("three\n\nlines");
		}
		
		@Test @DisplayName("stripComments() can strip comments.")
		void stripCommentsCanStripComments() {
			assertThat(StripComments.stripComments("one#1\ntwo # comment", new String[]{"#"}))
					.isEqualTo("one\ntwo");
		}
	}

	@Nested @DisplayName("removeComment()")
	class RemoveComment {

		@Test @DisplayName("can remove comment.")
		void canRemoveComment() {
			assertThat(StripComments.removeComment("command# comment", "#")).isEqualTo("command");
		}

		@Test @DisplayName("works without comment.")
		void worksWithoutComment() {
			assertThat(StripComments.removeComment("command", "#")).isEqualTo("command");
		}

		@Test @DisplayName("works with comment-only input.")
		void worksWithCommentOnlyInput() {
			assertThat(StripComments.removeComment("# comment", "#")).isEmpty();
		}
	}
	
	@Nested @DisplayName("removeComment() supporting multiple comment symbols")
	class RemoveCommentSupportingMultipleCommentSymbols {
		
		@Test @DisplayName("works")
		void works() {
			assertThat(StripComments.removeComment("one! comment", new String[] {"#", "!"})).isEqualTo("one");
			assertThat(StripComments.removeComment("two# comment", new String[] {"#", "!"})).isEqualTo("two");
		}
	}
	
	@Nested @DisplayName("acceptance tests")
	class AcceptanceTests {

		@Test
		public void stripComments() {
			assertEquals("apples, pears\ngrapes\nbananas", StripComments.stripComments( "apples, pears # and bananas\ngrapes\nbananas !apples", new String[] { "#", "!" } ));
			assertEquals("a\nc\nd", StripComments.stripComments( "a #b\nc\nd $e f g", new String[] { "#", "$" } ));
		}
	}
}
