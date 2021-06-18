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
		Command command = Command.lookUp(commands[0]);
		command.execute(this);
	}

	private enum Command {
		
		FORWARD('f') {
			@Override
			public void execute(final MarsRover marsRover) {
				marsRover.position = marsRover.direction.moveForward(marsRover.position);
			}
		},
		BACKWARD('b') {
			@Override
			public void execute(final MarsRover marsRover) {
				marsRover.position = marsRover.direction.moveBackwards(marsRover.position);
			}
		},
		TURN_LEFT('l') {
			@Override
			public void execute(final MarsRover marsRover) {
				marsRover.direction = marsRover.direction.turnLeft();
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

		public abstract void execute(final MarsRover marsRover);
	}
}
