package loopinspector;

public class Node {

	private Node next;

	public Node getNext() {
		return next;
	}

	public void setNext(final Node next) {
		this.next = next;
	}

	public static Node createChain(final int tailSize, final int loopSize) {

		Node firstTailNode = null;
		Node lastTailNode = null;
		for (int i = 0; i < tailSize; i++) {
			final Node node = new Node();
			if (firstTailNode == null) {
				firstTailNode = node;
			}
			if (lastTailNode != null) {
				lastTailNode.setNext(node);
			}
			lastTailNode = node;
		}

		Node firstLoopNode = null;
		Node lastLoopNode = null;

		for (int j = 0; j < loopSize; j++) {
			final Node node = new Node();
			if (firstLoopNode == null) {
				firstLoopNode = node;
			}
			if (lastLoopNode != null) {
				lastLoopNode.setNext(node);
			}
			lastLoopNode = node;
		}
		lastLoopNode.setNext(firstLoopNode);
		if (lastTailNode != null && firstLoopNode != null) {
			lastTailNode.setNext(firstLoopNode);
		}

		if (firstTailNode != null) {
			return firstTailNode;
		}
		if (firstLoopNode != null) {
			return firstLoopNode;
		}
		return null;
	}


}
