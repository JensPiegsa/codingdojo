package pacman;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("A Direction")
class DirectionTest {

	// TODO implement direction.getDeltaX() and direction.getDeltaY()
	
	@Test @DisplayName("up results in delta x of 0 and delta y of -1.")
	void upResultsInDeltaXOf0AndDeltaYOf1() {
		
	}
	
	@DisplayName("knows delta x and y.") 
	@ParameterizedTest(name = "{0} -> ({1}, {2})")
	@CsvSource({
			"up,0,-1",
			"down,0,1",
			"left,-1,0",
			"right,1,0",
	})
	void knowsDeltaXAndY(final Direction direction, final int expectedDeltaX, final int expectedDeltaY) {
		assertThat(direction.getDeltaX()).isEqualTo(expectedDeltaX);
		assertThat(direction.getDeltaY()).isEqualTo(expectedDeltaY);
	}
}