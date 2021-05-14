package montyhall;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class MontyHall {

	private final Random random;

	public MontyHall(final Random random) {
		this.random = random;
	}

	public int play(final int rounds, final MontyHallStrategy strategy) {
		int wonCount = 0;
		for (int i = 0; i < rounds; i++) {
			final boolean won = play(strategy);
			if (won) {
				wonCount++;
			}
		}
		return wonCount;
	}

	public boolean play(final MontyHallStrategy strategy) {
		final int prizeDoor = pickRandomDoorOutOfThree();
		final int firstPlayerDecision = pickRandomDoorOutOfThree();
		final int montysPick = getMontysPick(prizeDoor, firstPlayerDecision);
		final int finalPlayerPick = getFinalPlayerPick(firstPlayerDecision, montysPick, strategy); 
		return playSecondRound(prizeDoor, finalPlayerPick);
	}

	private int getFinalPlayerPick(final int firstPlayerDecision, final int montysPick, final MontyHallStrategy strategy) {
		switch (strategy) {
			case KEEP_DECISION:
				return firstPlayerDecision;
			case ALTER_DECISION:
				return Stream.of(1, 2, 3)
						.filter(door -> door != firstPlayerDecision && door != montysPick)
						.findAny().get();
			default:
				throw new IllegalArgumentException("strategy not supported");
		}
	}

	public int getMontysPick(final int prizeDoor, final int firstPlayerDecision) {
		final List<Integer> remainingDoors = Stream.of(1, 2, 3)
				.filter(door -> door != prizeDoor && door != firstPlayerDecision)
				.collect(toList());
		
		return pickRandomOutOf(remainingDoors);
	}

	private int pickRandomOutOf(final List<Integer> remainingDoors) {
		final int index = random.nextInt(remainingDoors.size());
		return remainingDoors.get(index);
	}

	boolean playSecondRound(final int prizeDoor, final int decisionDoor) {
		return prizeDoor == decisionDoor;
	}

	int pickRandomDoorOutOfThree() {
		return 1 + random.nextInt(3);
	}
}
