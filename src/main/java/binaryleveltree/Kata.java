package binaryleveltree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Kata {
    public static List<Integer> treeByLevels(final Node node) {

        final List<Integer> values = new ArrayList<>();
        final Queue<Node> visitLater = new LinkedList<>();
        visitLater.add(node);
        while (!visitLater.isEmpty()) {
            final Node current = visitLater.poll();
            if (current != null) {
                values.add(current.value);
                visitLater.add(current.left);
                visitLater.add(current.right);
            }
        }
        return values;
    }
}