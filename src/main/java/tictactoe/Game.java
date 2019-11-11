package tictactoe;

import java.awt.*;

public class Game {

	private Letter current = Letter.X;
	private Board board = new Board();

	public GameResult place(final Letter letter, final Point position) {
		if (current != letter) {
			throw new IllegalStateException();
		}

		GameResult result = board.add(letter,position);
		current = current.next(letter);

		return result;
	}

	public String printBoard() {
		return board.toString();
	}

}
