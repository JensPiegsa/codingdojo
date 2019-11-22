package tictactoe2;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static tictactoe2.PlayerMark.*;
import static tictactoe2.Position.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class Board {

	public static final int width = 3;

	private static final List<List<Position>> winningRows = asList(
			asList(topLeft, top, topRight),
			asList(left, middle, right),
			asList(bottomLeft, bottom, bottomRight),
			asList(topLeft, left, bottomLeft),
			asList(top, middle, bottom),
			asList(topRight, right, bottomRight),
			asList(topLeft, middle, bottomRight),
			asList(bottomLeft, middle, topRight));

	private final Map<Position, PlayerMark> markPositions = new LinkedHashMap<>();

	public void place(Position position, PlayerMark playerMark) {
		if (markPositions.containsKey(position)) {
			throw new IllegalArgumentException("Position already taken.");
		}
		if (checkWinner().isPresent()) {
			throw new IllegalStateException("Game is already over.");
		}
		markPositions.put(position, playerMark);
	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();

		final Position[] allPositions = Position.values();

		for (int i = 0; i < allPositions.length; i++) {
			insertLineBreakWhenNeeded(result, i);
			Position position = allPositions[i];
			PlayerMark playerMark = markPositions.getOrDefault(position, NONE);
			result.append(playerMark);
		}

		checkWinner()
			.map(PlayerMark::winningMessage)
			.ifPresent(result::append);

		return result.toString();
	}

	private void insertLineBreakWhenNeeded(StringBuilder result, int i) {
		if (i > 0 && i % width == 0) {
			result.append('\n');
		}
	}

	private Optional<PlayerMark> checkWinner() {

		final Optional<PlayerMark> winner = winner();
		if (winner.isPresent()) {
			return winner;
		}

		if (markPositions.size() == 9) {
			return Optional.of(NONE);
		}

		return Optional.empty();
	}

	private Optional<PlayerMark> winner() {
		return Stream.of(X, O)
				.filter(this::checkWon)
				.findFirst();
	}

	private boolean checkWon(PlayerMark playerMark) {
		return winningRows.contains(allPositionsOf(playerMark));
	}

	private List<Position> allPositionsOf(PlayerMark mark) {

		return markPositions.entrySet().stream()
				.filter(e -> e.getValue() == mark)
				.map(Map.Entry::getKey)
				.collect(toList());
	}
}
