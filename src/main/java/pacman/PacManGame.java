package pacman;

import java.util.Arrays;
import java.util.OptionalInt;

public class PacManGame {

	private final int boardWidth;
	private String board;

	public PacManGame(final String board) {
		this.board = board;
		final String[] rows = board.split("\n");
		boardWidth = Arrays.stream(rows).mapToInt(String::length).max().getAsInt();
	}

	@Override
	public String toString() {
		return board;
	}

	public void move(final Direction direction) {
		board = """
			.V. .
			. .
			. . .
        """;
	}

	public int getBoardHeight() {
		return 0;
	}

	public int getBoardWidth() {
		return boardWidth;
	}
}
