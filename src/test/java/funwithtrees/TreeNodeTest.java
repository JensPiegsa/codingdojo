package funwithtrees;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("A TreeNode")
class TreeNodeTest {

	@Nested @DisplayName("has equals() method")
	class HasEqualsMethod {
		
		@Test @DisplayName("invoked with null returning false.")
		void invokedWithNullReturningFalse() {
			assertThat(new TreeNode(1).equals(null)).isFalse();
		}
	
		@Test @DisplayName("invoked with same returning true.")
		void invokedWithSameReturningTrue() {
			final TreeNode node = new TreeNode(1);
			assertThat(node.equals(node)).isTrue();
		}
		
		@Test @DisplayName("invoked with equal value returning true.")
		void invokedWithEqualValueReturningTrue() {
			final TreeNode nodeA = new TreeNode(1);
			final TreeNode nodeB = new TreeNode(1);
			assertThat(nodeA.equals(nodeB)).isTrue();
		}
		
		@Test @DisplayName("invoked with unequal values returning false.")
		void invokedWithUnequalValuesReturningFalse() {
			final TreeNode nodeA = new TreeNode(1);
			final TreeNode nodeB = new TreeNode(2);
			assertThat(nodeA.equals(nodeB)).isFalse();
		}
		@Test @DisplayName("invoked with equal values but different left child returning false.")
		void invokedWithEqualValuesButDifferentLeftChildReturningFalse() {
			final TreeNode nodeA = new TreeNode(1, new TreeNode(11), null);
			final TreeNode nodeB = new TreeNode(1, new TreeNode(12), null);
			
			assertThat(nodeA.equals(nodeB)).isFalse();
		}
		
		@Test @DisplayName("invoked with equal values but different right child returning false.")
		void invokedWithEqualValuesButDifferentRightChildReturningFalse() {
			final TreeNode nodeA = new TreeNode(1, null, new TreeNode(11));
			final TreeNode nodeB = new TreeNode(1, null, new TreeNode(12));
			
			assertThat(nodeA.equals(nodeB)).isFalse();
		}
		
		@Test @DisplayName("invoked with equal values and left child but different right child returning false.")
		void invokedWithEqualValuesAndLeftChildButDifferentRightChildReturningFalse() {
			final TreeNode nodeA = new TreeNode(1, new TreeNode(11), new TreeNode(22));
			final TreeNode nodeB = new TreeNode(1, new TreeNode(11), new TreeNode(23));

			assertThat(nodeA.equals(nodeB)).isFalse();
		}
		
		@Test @DisplayName("invoked with complex equal tree returning true.")
		void invokedWithComplexEqualTreeReturningTrue() {
			final TreeNode nodeA = new TreeNode(1, 
					new TreeNode(11, 
							new TreeNode(3, new TreeNode(3), new TreeNode(4)), 
							new TreeNode(5)), 
					new TreeNode(22));
			final TreeNode nodeB = new TreeNode(1, 
					new TreeNode(11, 
							new TreeNode(3, new TreeNode(3), new TreeNode(4)), 
							new TreeNode(5)), 
					new TreeNode(22));

			assertThat(nodeA.equals(nodeB)).isTrue();
		}
		
		@Test @DisplayName("invoked with complex unequal tree returning false.")
		void invokedWithComplexUnequalTreeReturningFalse() {
			final TreeNode nodeA = new TreeNode(1,
					new TreeNode(11,
							new TreeNode(3, new TreeNode(3), new TreeNode(4)),
							new TreeNode(5)),
					new TreeNode(22));
			final TreeNode nodeB = new TreeNode(1,
					new TreeNode(11,
							new TreeNode(3, new TreeNode(3), new TreeNode(666)),
							new TreeNode(5)),
					new TreeNode(22));

			assertThat(nodeA.equals(nodeB)).isFalse();
		}
		
		@Test @DisplayName("invoked with other object returns false.")
		void invokedWithOtherObjectReturnsFalse() {
			assertThat(new TreeNode(1).equals(new Object())).isFalse();
		}

		@Test @DisplayName("bug")
		void bug() {
			final TreeNode a = new TreeNode(3, new TreeNode(4), null);
			final TreeNode b = new TreeNode(3);
			assertThat(a.equals(b)).isFalse();
			assertThat(b.equals(a)).isFalse();
		}
	}
}