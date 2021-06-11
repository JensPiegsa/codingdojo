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
}
