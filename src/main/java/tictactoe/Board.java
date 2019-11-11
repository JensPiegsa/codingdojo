package tictactoe;

import java.awt.*;

public class Board {
	private Letter[][] board = new Letter[3][3];

	public GameResult add(Letter letter, Point point) {
		if (board[point.x][point.y] != null)
			throw new IllegalArgumentException();

		board[point.x][point.y] = letter;
		if (checkHorizontalTriple(letter, point))
			return (letter == Letter.X ? GameResult.X_Win : GameResult.O_Win);
		if (checkVerticalTriple(letter, point))
			return (letter == Letter.X ? GameResult.X_Win : GameResult.O_Win);
		if (checkDiagonalTriple(letter))
			return (letter == Letter.X ? GameResult.X_Win : GameResult.O_Win);
		if (checkDiagonalBackwardsTriple(letter))
			return (letter == Letter.X ? GameResult.X_Win : GameResult.O_Win);

		return GameResult.GO_ON;
	}

	private boolean checkDiagonalBackwardsTriple(final Letter letter) {
		for (int i = 0; i < 3; i++) {
			if (board[i][2 - i] == null || board[i][2 - i] != letter) {
				return false;
			}
		}
		return true;
	}

	private boolean checkDiagonalTriple(final Letter letter) {
		for (int i = 0; i < 3; i++) {
			if (board[i][i] == null || board[i][i] != letter) {
				return false;
			}
		}
		return true;
	}

	private boolean checkVerticalTriple(final Letter letter, final Point point) {
		for (int i = 0; i < 3; i++) {
			if (board[point.x][i] == null || board[point.x][i] != letter) {
				return false;
			}
		}
		return true;
	}

	private boolean checkHorizontalTriple(Letter letter, Point point) {
		for (int i = 0; i < 3; i++) {
			if (board[i][point.y] == null || board[i][point.y] != letter) {
				return false;
			}
		}
		return true;
	}


	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("\n");
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				Letter l = board[x][y];
				s.append( l== null ? "_" : l);
				if (x < 2)
					s.append(" ");
			}
			if (y < 2)
			s.append("\n");
		}
		return s.toString();
	}
}
