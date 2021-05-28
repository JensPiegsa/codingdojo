package yatzy;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

public class Yatzy {

	public static int chance(final List<Integer> dices) {
//		return dices.stream().collect(Collectors.summingInt(i -> i));
		return dices.stream().mapToInt(i -> i).sum();
//		return dices.stream().reduce(0,Integer::sum);
	}

	public static int yatzy(final List<Integer> dices) {
		final int number = dices.get(0);
		return dices.stream().filter(dice -> dice.equals(number)).count() == 5 ? 50 : 0;
	}

	public static int ones(final List<Integer> dices) {
		return sumForCountingNumbers(dices, 1);
	}

	public static int twos(final List<Integer> dices) {
		return sumForCountingNumbers(dices, 2);
	}

	public static int threes(final List<Integer> dices) {
		return sumForCountingNumbers(dices, 3);
	}

	public static int fours(final List<Integer> dices) {
		return sumForCountingNumbers(dices, 4);
	}

	public static int fives(final List<Integer> dices) {
		return sumForCountingNumbers(dices, 5);
	}

	public static int sixes(final List<Integer> dices) {
		return sumForCountingNumbers(dices, 6);
	}

	private static int sumForCountingNumbers(final List<Integer> dices, final int countingNumber) {
		return dices.stream().filter(dice -> dice.equals(countingNumber)).mapToInt(i -> i).sum();
	}

	public static int pair(final List<Integer> dices) {
		final List<Integer> sorted = dices.stream().sorted(reverseOrder()).collect(toList());

		List<Integer> sortedPairs = new ArrayList<>();
		for (int i = 0; i < sorted.size() - 1; i++) {
			if (sorted.get(i) == sorted.get(i + 1)) {
				sortedPairs.add(sorted.get(i));
			}
		}
		return sortedPairs.isEmpty() ? 0 : sortedPairs.get(0) * 2;
	}
}
