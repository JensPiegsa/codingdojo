package binaryleveltree;

public class Node {

    public final Node left;
    public final Node right;
    public final int value;

    public Node(final Node l, final Node r, final int v) {
        left = l;
        right = r;
        value = v;
    }
}