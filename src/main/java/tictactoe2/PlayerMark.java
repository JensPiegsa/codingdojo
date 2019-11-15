package tictactoe2;

public enum PlayerMark {

	NONE(" ", "\nDraw!"),
	X("X", "\nFirst player won!"),
	O("O", "\nSecond player won!");

	private final String s;
	private final String winningMessage;

	PlayerMark(final String s, final String winningMessage) {
		this.s = s;
		this.winningMessage = winningMessage;
	}

	@Override public String toString() {
		return s;
	}

	PlayerMark alternate() {
		return this == X ? O : X;
	}

	public String winningMessage() {
		return winningMessage;
	}
}
