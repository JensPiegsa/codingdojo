package marsrover;

public class MarsRover {

	private Position position;
	private Direction direction;

	public MarsRover(Position position, Direction direction) {
		this.position = position;
		this.direction = direction;
	}

	public MarsRover() {
		this(Position.of(0, 0), Direction.NORTH);
	}

	public Position getPosition() {
		return position;
	}

	public Direction getDirection() {
		return direction;
	}

	public void move(final char[] commands) {
		position = direction.moveForward(position);
	}
}
