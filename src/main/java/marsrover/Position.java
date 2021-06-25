package marsrover;

import java.util.List;
import java.util.Objects;

public class Position {
	
	private int x;
	private int y;
	
	private Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public static Position of(final int x, final int y) {
		return new Position(x, y);
	}

	@Override
	public String toString() {
		return "Position{" +
				"x=" + x +
				", y=" + y +
				'}';
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (!(o instanceof Position)) return false;
		final Position position = (Position) o;
		return x == position.x && y == position.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
