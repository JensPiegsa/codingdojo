package marsrover;

public class MarsRover {

	private final Position position;
	private final Direction direction;

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
}
