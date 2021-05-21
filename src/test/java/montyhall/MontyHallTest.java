package montyhall;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Random;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MontyHallTest {
	
	@Test @DisplayName("test KEEP_DECISION strategy.")
	void testKeepDecisionStrategy() {
		final Random random = new Random(1);
		final MontyHall montyHall = new MontyHall(random);
		final int rounds = 1000;
		final MontyHallStrategy strategy = MontyHallStrategy.KEEP_DECISION;
		
		final int wins = montyHall.play(rounds, strategy);
		
		then(wins).isEqualTo(323);
	}
	
	@Test @DisplayName("test ALTER_DECISION strategy.")
	void testAlterDecisionStrategy() {
		final Random random = new Random(1);
		final MontyHall montyHall = new MontyHall(random);
		final int rounds = 1000;
		final MontyHallStrategy strategy = MontyHallStrategy.ALTER_DECISION;
		
		final int wins = montyHall.play(rounds, strategy);
		
		then(wins).isEqualTo(677);
	}
	
	@Test @DisplayName("test one game with keep strategy wins.")
	void testOneGameWithKeepStrategyWins() {
		final Random random = new Random(3);
		final MontyHall montyHall = new MontyHall(random);
		final MontyHallStrategy strategy = MontyHallStrategy.KEEP_DECISION;
		
		final boolean won = montyHall.play(strategy);
		
		then(won).isTrue();
	}
	
	@DisplayName("a player choosing same door as monty wins.")
	@ParameterizedTest(name = "door {0}")
	@CsvSource({"1", "2", "3"})
	void playerChoosingSameDoorAsMontyWins(final int door) {
		final Random random = new Random(1);
		final MontyHall montyHall = new MontyHall(random);
		final MontyHallStrategy strategy = MontyHallStrategy.KEEP_DECISION;

		final int montyDoor = door;
		final int playerDoor = door;
		final boolean won = montyHall.playSecondRound(montyDoor, playerDoor);
		
		then(won).isTrue();
	}

	@DisplayName("a player choosing different door as monty looses.")
	@ParameterizedTest(name = "monty hides prize behind door {0} and player chooses door {1}")
	@CsvSource({"1,2", "1,3", "2,1", "2,3", "3,1", "3,2"})
	void playerChoosingDifferentDoorAsMontyLooses(final int montyDoor, final int playerDoor) {
		final Random random = new Random(1);
		final MontyHall montyHall = new MontyHall(random);
		final MontyHallStrategy strategy = MontyHallStrategy.KEEP_DECISION;

		final boolean won = montyHall.playSecondRound(montyDoor, playerDoor);

		then(won).isFalse();
	}
	
	@ParameterizedTest @DisplayName("test random door numbers.")
	@CsvSource({"1,1", "1,1", "2,2", "3,3", "4,3", "5,3", "6,2"})
	void testRandomDoorNumbers(final int seed, final int expectedDoor) {
		final Random random = new Random(seed);
		final MontyHall montyHall = new MontyHall(random);

		final int doorNumber = montyHall.pickRandomDoorOutOfThree();
		
		then(doorNumber).isEqualTo(expectedDoor);
	}
	
	@Test @DisplayName("test random door number trustworthy valid.")
	void testRandomDoorNumberTrustworthyValid() {
		final Random random = new Random(1);
		final MontyHall montyHall = new MontyHall(random);

		IntStream.range(0, 1000).map(i -> montyHall.pickRandomDoorOutOfThree())
				.forEach(doorNumber -> then(doorNumber).isBetween(1, 3));
	}
	
	@ParameterizedTest @DisplayName("test simple cases of montys pick.")
	@CsvSource({"1,2,3", "1,3,2", "2,1,3", "2,3,1", "3,1,2", "3,2,1"})
	void testSimpleCasesOfMontysPick(final int prizeDoor, final int firstPlayerDecision, final int expectedMontyPick) {
		final Random random = new Random(1);
		final MontyHall montyHall = new MontyHall(random);

		final int montysPick = montyHall.getMontysPick(prizeDoor, firstPlayerDecision);
		then(montysPick).isEqualTo(expectedMontyPick);
	}
	
	@ParameterizedTest @DisplayName("test cases of montys random hint.")
	@CsvSource({"13383,1,2", "4,1,3", "12733,2,1", "5,2,3"})
	void testCasesOfMontysRandomHint(final long seed, final int door, final int expectedMontyPick) {
		final Random random = new Random(seed);
		final MontyHall montyHall = new MontyHall(random);

		final int prizeDoor = door;
		final int firstPlayerDecision = door;
		final int montysPick = montyHall.getMontysPick(prizeDoor, firstPlayerDecision);
		then(montysPick).isEqualTo(expectedMontyPick);
	}
	
	// TODO test getFinalPlayerPick()
}
