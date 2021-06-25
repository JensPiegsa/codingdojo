package marsrover;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("A mars rover")
class MarsRoverTest {
	
	MarsRover rover;
	@Mock Sensor sensor;

	@Test @DisplayName("starts at position (0,0).")
	void startsAtPositionZeroZero() {
		rover = new MarsRover();
		assertThat(rover.getPosition()).isEqualTo(Position.of(0, 0));
	}

	@Test
	@DisplayName("has an initial position at x and y.")
	void hasAnInitialPositionAtXAndY() {

	    // Act
	    rover = new MarsRover(Position.of(123, 456), Direction.NORTH, null);

	    // Assert
		assertThat(rover.getPosition()).isEqualTo(Position.of(123, 456));
	}

	@Test
	@DisplayName("has an initial facing direction.")
	void hasAnInitialFacingDirection() {

	    // Act
	    rover = new MarsRover(Position.of(0, 0), Direction.NORTH, null);

	    // Assert
		assertThat(rover.getDirection()).isEqualTo(Direction.NORTH);
	}
	
	@Test @DisplayName("can move forward facing north.")
	void canMoveForwardFacingNorth() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.NORTH, sensor);
		char[] givenCommands = {'f'};

		// when
		final boolean obstacle = rover.move(givenCommands);

		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(0,1));
	}

	@Test @DisplayName("can move forward facing east.")
	void canMoveForwardFacingEast() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.EAST, sensor);
		char[] givenCommands = {'f'};

		// when
		rover.move(givenCommands);

		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(1,0));
	}

	@Test @DisplayName("can move forward facing west.")
	void canMoveForwardFacingWest() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.WEST, sensor);
		char[] givenCommands = {'f'};

		// when
		rover.move(givenCommands);

		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(-1,0));
	}

	@Test @DisplayName("can move backwards facing west.")
	void canMoveBackwardsFacingWest() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.WEST, sensor);
		char[] givenCommands = {'b'};

		// when
		rover.move(givenCommands);

		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(1,0));
	}

	@Test @DisplayName("can turn left facing east.")
	void canTurnLeftFacingEast() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.EAST, sensor);
		char[] givenCommands = {'l'};

		// when
		rover.move(givenCommands);

		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(0,0));
		assertThat(rover.getDirection()).isEqualTo(Direction.NORTH);
	}
	
	@Test @DisplayName("can turn right facing east.")
	void canTurnRightFacingEast() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.EAST, sensor);
		char[] givenCommands = {'r'};

		// when
		rover.move(givenCommands);

		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(0,0));
		assertThat(rover.getDirection()).isEqualTo(Direction.SOUTH);
	}

	@Test
	@DisplayName("can move 3 steps.")
	void canMoveThreeSteps() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.EAST, sensor);
		char[] givenCommands = {'f', 'l', 'b'};

		// when
		rover.move(givenCommands);

		// then
		assertThat(rover.getPosition()).isEqualTo(Position.of(1,-1));
		assertThat(rover.getDirection()).isEqualTo(Direction.NORTH);
	}

	@Test @DisplayName("can sense obstacle when moving forward.")
	void canSenseObstacleWhenMovingForward() {
		// given
		rover = new MarsRover(Position.of(0, 0), Direction.EAST, sensor);
		char[] givenCommands = {'f'};
		given(sensor.hasFrontObstacle()).willReturn(true);

		// when
		boolean obstacle = rover.move(givenCommands);

		// then
		assertThat(obstacle).isTrue();
		assertThat(rover.getPosition()).isEqualTo(Position.of(0,0));
	}
}
