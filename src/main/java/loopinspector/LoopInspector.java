package loopinspector;

import java.util.LinkedList;

public class LoopInspector {

	public int loopSize(final Node firstTailNode) {

		final var inspectedNodes = new LinkedList<Node>();
		Node node = firstTailNode.getNext();
		Node firstLoopNode = null;
		while (node != null) {
			if (inspectedNodes.contains(node)) {
				firstLoopNode = node;
				break;
			} else {
				inspectedNodes.add(node);
			} 
			node = node.getNext();
		}

		final var nodeIterator = inspectedNodes.descendingIterator();
		int loopSize = 1;
		while (nodeIterator.hasNext() && nodeIterator.next() != firstLoopNode) {
			loopSize++;
		}

		return loopSize;
	}
}
