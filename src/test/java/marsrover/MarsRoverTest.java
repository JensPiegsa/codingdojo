package marsrover;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A mars rover")
class MarsRoverTest {
	
	MarsRover rover = new MarsRover();

	@Test @DisplayName("starts at position (0,0).")
	void startsAtPositionZeroZero() {
		assertThat(rover.getPosition()).isEqualTo(Position.of(0, 0));
	}

	@Test
	@DisplayName("has an initial position at x and y")
	void hasAnInitialPositionAtXAndY() {

	    // Act
	    rover = new MarsRover(Position.of(123, 456), Direction.NORTH);

	    // Assert
		assertThat(rover.getPosition()).isEqualTo(Position.of(123, 456));
	}

	@Test
	@DisplayName("has an initial facing direction.")
	void hasAnInitialFacingDirection() {

	    // Act
	    rover = new MarsRover(Position.of(0, 0), Direction.NORTH);

	    // Assert
		assertThat(rover.getDirection()).isEqualTo(Direction.NORTH);
	}
	
	@Test @DisplayName("can move forward facing north.")
	void canMoveForwardFacingNorth() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.NORTH);
		char[] givenCommands = {'f'};

		// when
		rover.move(givenCommands);
		
		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(0,1));
	}

	@Test @DisplayName("can move forward facing east.")
	void canMoveForwardFacingEast() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.EAST);
		char[] givenCommands = {'f'};

		// when
		rover.move(givenCommands);

		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(1,0));
	}

	@Test @DisplayName("can move forward facing west.")
	void canMoveForwardFacingWest() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.WEST);
		char[] givenCommands = {'f'};

		// when
		rover.move(givenCommands);

		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(-1,0));
	}

	@Test @DisplayName("can move backwards facing west.")
	void canMoveBackwardsFacingWest() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.WEST);
		char[] givenCommands = {'b'};

		// when
		rover.move(givenCommands);

		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(1,0));
	}

	@Test @DisplayName("can turn left facing east.")
	void canTurnLeftFacingEast() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.EAST);
		char[] givenCommands = {'l'};

		// when
		rover.move(givenCommands);

		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(0,0));
		assertThat(rover.getDirection()).isEqualTo(Direction.NORTH);
	}
}
