package tictactoe2;

public enum Position {

	topLeft(0, 0), top(1, 0), topRight(2, 0),
	left(0, 1), middle(1, 1), right(1, 1),
	bottomLeft(0, 2), bottom(1, 2), bottomRight(1, 2);

	private final int x;
	private final int y;

	Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
}
