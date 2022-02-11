package pacman;

public enum Direction {
	left(-1,0),
	up(0,-1),
	right(1,0),
	down(0,1);

	private final int deltaX;
	private final int deltaY;

	Direction(final int deltaX, final int deltaY) {

		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public int getDeltaX() {
		return deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}
}
