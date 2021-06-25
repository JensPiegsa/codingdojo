package marsrover;

public class MarsRover {

	private Position position;
	private Direction direction;
	private Sensor sensor;

	public MarsRover(Position position, Direction direction, final Sensor sensor) {
		this.position = position;
		this.direction = direction;
		this.sensor = sensor;
	}

	public MarsRover() {
		this(Position.of(0, 0), Direction.NORTH, null);
	}

	public Position getPosition() {
		return position;
	}

	public Direction getDirection() {
		return direction;
	}

	public boolean move(final char[] commandCharacters) {
		for (char commandCharacter : commandCharacters) {
			Command command = Command.lookUp(commandCharacter);
			boolean obstacle = command.execute(this);
			if (obstacle) {
				return true;
			}
		}
		return false;
	}

	private enum Command {
		
		FORWARD('f') {
			@Override
			public boolean execute(final MarsRover marsRover) {
				if (marsRover.sensor.hasFrontObstacle()) {
					return true;
				}
				marsRover.position = marsRover.direction.moveForward(marsRover.position);
				return false;
			}
		},
		BACKWARD('b') {
			@Override
			public boolean execute(final MarsRover marsRover) {
				if (marsRover.sensor.hasBackObstacle()) {
					return true;
				}
				marsRover.position = marsRover.direction.moveBackwards(marsRover.position);
				return false;
			}
		},
		TURN_LEFT('l') {
			@Override
			public boolean execute(final MarsRover marsRover) {
				marsRover.direction = marsRover.direction.turnLeft();
				return false;
			}
		},
		TURN_RIGHT('r') {
			@Override
			public boolean execute(final MarsRover marsRover) {
				marsRover.direction = marsRover.direction.turnRight();
				return false;
			}
		};

		private final char commandChar;

		Command(final char commandChar) {
			this.commandChar = commandChar;
		}

		public static Command lookUp(final char commandChar) {
			for (Command command : values()) {
				if (command.commandChar == commandChar) {
					return command;
				}
			}
			throw new IllegalArgumentException("Unknown command: " + commandChar);
		}

		public abstract boolean execute(final MarsRover marsRover);
	}
}
