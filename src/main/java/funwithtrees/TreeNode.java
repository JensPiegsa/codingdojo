package funwithtrees;

class TreeNode {

	TreeNode left;
	TreeNode right;
	int value;

	TreeNode(int value, TreeNode left, TreeNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	TreeNode(int value) {
		this(value, null, null);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}

		if (this == other) {
            return true;
        }
        
        if (!(other instanceof TreeNode)) {
            return false;
        }
        
		final TreeNode otherNode = (TreeNode) other;
		if (value == otherNode.value) {
            if (left == null && right == null) {
                return true;
            }
			if (left != null && right == null) {
				return left.equals(otherNode.left);
			}
			if (left == null && right != null) {
				return right.equals(otherNode.right);
			}
            
			return left.equals(otherNode.left) && right.equals(otherNode.right);
		}
		return false;
	}
}