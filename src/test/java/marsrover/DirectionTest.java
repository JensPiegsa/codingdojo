package marsrover;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A Direction")
class DirectionTest {

	@Test @DisplayName("turning right from north is east.")
	void turningRightFromNorthIsEast() {
		assertThat(Direction.NORTH.turnRight()).isEqualTo(Direction.EAST);
	}
}