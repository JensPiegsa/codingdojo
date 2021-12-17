package aoc2021.day02;

public enum SubmarineCommand {

	down(1, 0),
	up(-1, 0),
	forward(0, 1);

	private final int depthFactor;
	private final int horizontalFactor;

	SubmarineCommand(final int depthFactor, final int horizontalFactor) {
		this.depthFactor = depthFactor;
		this.horizontalFactor = horizontalFactor;
	}

	public int getDepthFactor() {
		return depthFactor;
	}

	public int getHorizontalFactor() {
		return horizontalFactor;
	}
}
