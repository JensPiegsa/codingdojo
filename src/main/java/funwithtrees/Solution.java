package funwithtrees;

public class Solution {
    
    static TreeNode arrayToTree(int[] array) {
        if (array.length == 0) {
            return null;
        }
        final TreeNode root = new TreeNode(array[0]);
        final TreeNode currentParent = root;

        final TreeNode leftChild = null;
        final TreeNode rightChild = null;

        // every even node (except 0) is a right child
        // every odd node is a left child
        for (int i = array.length - 1; i >= 0; i--) {
            final int n = array[i];
            final TreeNode treeNode = new TreeNode(i, leftChild, rightChild);

        }

        return root;
    }
}