package pacman;

import java.util.Arrays;
import java.util.OptionalInt;

public class PacManGame {


	private PacManBoard board;

	public PacManGame(final String board) {
		this.board = PacManBoard.parse(board);
	}

	@Override
	public String toString() {
		return board.toString();
	}

	public void move(final Direction direction) {
//		board = """
//			.V. .
//			. .
//			. . .
//        """;
	}

	public int getBoardHeight() {
		return board.getBoardHeight();
	}

	public int getBoardWidth() {
		return board.getBoardWidth();
	}
}
