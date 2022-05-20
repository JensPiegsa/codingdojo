package loopinspector;

import java.util.Iterator;
import java.util.LinkedList;

public class LoopInspector {

	public int loopSize(Node firstTailNode) {

		final int loopSize = 0;

		final LinkedList<Node> inspectedNodes = new LinkedList<>();
		Node aNode = firstTailNode.getNext();
		Node firstLoopNode = null;
		while (aNode != null) {

			if (inspectedNodes.contains(aNode)) {
//				return loopSize;
				firstLoopNode = aNode;
				break;
			} else {
				inspectedNodes.add(aNode);
			} 
			aNode = aNode.getNext();
		}

		final Iterator<Node> nodeIterator = inspectedNodes.descendingIterator();

		return 0;
	}
}
