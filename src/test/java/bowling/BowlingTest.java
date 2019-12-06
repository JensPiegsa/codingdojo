package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingTest {

	/* Rules:

	Each game, or "line" of bowling, includes ten turns, or "frames" for the bowler.

	In each frame, the bowler gets up to two tries to knock down all the pins.

	If in two tries, he fails to knock them all down, his score for that frame is the total number of pins knocked down in his two tries.

	If in two tries he knocks them all down, this is called a "spare" and his score for the frame is ten plus the number of pins knocked down on his next throw (in his next turn).
	10 Points + 1 next try

	If on his first try in the frame he knocks down all the pins, this is called a "strike". His turn is over, and his score for the frame is ten plus the simple total of the pins knocked down in his next two rolls.
	10 Points + 2 next tries

	If he gets a spare or strike in the last (tenth) frame, the bowler gets to throw one or two more bonus balls, respectively. - These bonus throws are taken as part of the same turn. If the bonus throws knock down all the pins, the process does not repeat: the bonus throws are only used to calculate the score of the final frame.

	The game score is the total of all frame scores.

	 */

	@Test @DisplayName("complete frame")
	void completeFrame() {
		// given
		String frames = "12345123451234512345";

		// when
		Bowling bowling = new Bowling(frames);
		int totalScore = bowling.getTotalScore();

		// then
		assertThat(totalScore).isEqualTo(60);
	}

	@Test @DisplayName("one frame")
	void oneFrame() {
		// given
		String frames = "11111111111111111111";

		// when
		Bowling bowling = new Bowling(frames);
		int totalScore = bowling.getTotalScore();

		// then
		assertThat(totalScore).isEqualTo(20);
	}

	@Test @DisplayName("test with zeros")
	void testWithZeros() {
		// given
		String frames = "9-9-9-9-9-9-9-9-9-9-";

		// when
		Bowling bowling = new Bowling(frames);
		int totalScore = bowling.getTotalScore();

		// then
		assertThat(totalScore).isEqualTo(90);
	}

	@Test @DisplayName("with spares")
	void withSpares() {
		// given
		String frames = "5/1-----------------";

		// when
		Bowling bowling = new Bowling(frames);
		int totalScore = bowling.getTotalScore();

		// then
		assertThat(totalScore).isEqualTo(12);
	}

	@Test @DisplayName("multi spares")
	void multiSpares() {
		// given
		String frames = "5/5/5/5/5/5/5/5/5/5/5";

		// when
		Bowling bowling = new Bowling(frames);
		int totalScore = bowling.getTotalScore();

		// then
		assertThat(totalScore).isEqualTo(150);
	}

	@Test @DisplayName("with strike")
	void withStrike() {
		// given
		String frames = "X-1----------------";

		// when
		Bowling bowling = new Bowling(frames);
		int totalScore = bowling.getTotalScore();

		// then
		assertThat(totalScore).isEqualTo(12);
	}

	@Test @DisplayName("all strikes")
	void allStrikes() {
		// given
		String frames = "XXXXXXXXXXXX";

		// when
		Bowling bowling = new Bowling(frames);
		int totalScore = bowling.getTotalScore();

		// then
		assertThat(totalScore).isEqualTo(300);
	}

}
