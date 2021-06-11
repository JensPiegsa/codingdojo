package marsrover;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A Position")
class PositionTest {

	@Test @DisplayName("is equal to another position by x and y coordinates.")
	void isEqualToAnotherPositionByXAndYCoordinates() {
		assertThat(Position.of(0, 0)).isEqualTo(Position.of(0, 0));
	}
}