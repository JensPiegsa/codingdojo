package tictactoe;

public enum Letter {
	O, X;

	Letter next(final Letter letter) {
		return letter == X ? O : X;
	}
}
