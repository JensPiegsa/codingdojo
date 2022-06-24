package funwithtrees;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SolutionTest {
	
	// https://www.codewars.com/kata/57e5a6a67fbcc9ba900021cd/train/java


	@Test @DisplayName("empty array results in null.")
	void emptyArrayResultsInNull() {
		assertThat(Solution.arrayToTree(new int[] {})).isNull();
	}


	@Test @DisplayName("array with single value of 1 results in one tree node.")
	void arrayWithSingleValueOf1ResultsInOneTreeNode() {
		assertThat(Solution.arrayToTree(new int[] { 1 })).isEqualTo(new TreeNode(1));
	}

	@Test @DisplayName("array with single value of 2 results in one tree node.")
	void arrayWithSingleValueOf2ResultsInOneTreeNode() {
		assertThat(Solution.arrayToTree(new int[] { 2 })).isEqualTo(new TreeNode(2));
	}

	@Test @DisplayName("array with two values results in tree node with child.")
	void arrayWithTwoValuesResultsInTreeNodeWithChild() {
		assertThat(Solution.arrayToTree(new int[] { 3, 4 }))
				.isEqualTo(new TreeNode(3, new TreeNode(4), null));
	}

	@Test
	public void emptyArray() {
		TreeNode expected = null;
		MatcherAssert.assertThat(Solution.arrayToTree(arrayFrom()), is(expected));
	}

	@Test
	public void arrayWithMultipleElements() {
		TreeNode expected = new TreeNode(17, new TreeNode(0, new TreeNode(3), new TreeNode(15)), new TreeNode(-4));
		MatcherAssert.assertThat(Solution.arrayToTree(arrayFrom(17, 0, -4, 3, 15)), is(expected));
	}

	private int[] arrayFrom(int... values) {
		return values;
	}
}