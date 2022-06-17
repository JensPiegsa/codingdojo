package funwithtrees;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;

public class SolutionTest {
	
	// https://www.codewars.com/kata/57e5a6a67fbcc9ba900021cd/train/java

	@Test
	public void emptyArray() {
		TreeNode expected = null;
		assertThat(Solution.arrayToTree(arrayFrom()), is(expected));
	}

	@Test
	public void arrayWithMultipleElements() {
		TreeNode expected = new TreeNode(17, new TreeNode(0, new TreeNode(3), new TreeNode(15)), new TreeNode(-4));
		assertThat(Solution.arrayToTree(arrayFrom(17, 0, -4, 3, 15)), is(expected));
	}

	private int[] arrayFrom(int... values) {
		return values;
	}
}