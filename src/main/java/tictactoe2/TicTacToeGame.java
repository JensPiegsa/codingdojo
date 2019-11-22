package tictactoe2;

import static tictactoe2.PlayerMark.X;

public class TicTacToeGame {

	private final Board board = new Board();
	private PlayerMark nextPlayerMark = X;

	public void place(Position... positions) {
		for (Position position : positions) {
			board.place(position, nextPlayerMark);
			nextPlayerMark = nextPlayerMark.alternate();
		}
	}

	@Override public String toString() {
		return board.toString();
	}
}
