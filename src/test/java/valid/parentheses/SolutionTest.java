package valid.parentheses;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;
import valid.parentheses.Solution;

// TODO: Replace examples and use TDD by writing your own tests

public class SolutionTest {
	
	@Test
	public void sampleTest() {
		// assertEquals("expected", "actual");
		assertEquals(true, Solution.validParentheses( "()" ));
		assertEquals(false,Solution.validParentheses( "())" ));
		assertEquals(true,Solution.validParentheses( "32423(sgsdg)" ));
		assertEquals(false,Solution.validParentheses( "(dsgdsg))2432" ));
		assertEquals(true,Solution.validParentheses( "adasdasfa" ));
	}

	@Test
	public void testEmpty() {
		assertThat(Solution.validParentheses("")).isTrue();
	}

	@Test
	public void singleOpenParenthesesIsInvalid() {
		assertThat(Solution.validParentheses("(")).isFalse();
	}

	@Test
	public void singleClosingParenthesesIsInvalid() {
		assertThat(Solution.validParentheses(")")).isFalse();
	}

	@Test
	public void singlePairIsValid() {
		assertThat(Solution.validParentheses( "()" )).isTrue();
	}

	@Test
	public void singlePairInWrongOrderIsInvalid() {
		assertThat(Solution.validParentheses( ")(" )).isFalse();
	}
}
