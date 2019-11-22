package tictactoe2;

import static java.util.Arrays.asList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static tictactoe2.Position.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("In Tic Tac Toe") class TicTacToeGameTest {

	@Test @DisplayName("X always goes first.") void firstRule() {
		TicTacToeGame game = new TicTacToeGame();
		game.place(topLeft);
		then(game).hasToString("X  \n   \n   ");
	}

	@Test @DisplayName("players alternate placing Xs and Os on the board.") void secondRule() {
		TicTacToeGame game = new TicTacToeGame();
		game.place(topLeft, middle, bottomLeft);
		then(game).hasToString("X  \n O \nX  ");
	}

	@Test @DisplayName("players cannot play on a played position.") void thirdRule() {
		TicTacToeGame game = new TicTacToeGame();
		Executable executable = () -> game.place(top, top);
		assertThrows(IllegalArgumentException.class, executable);
	}

	@ParameterizedTest @MethodSource("moves") @DisplayName("a player with three Xs or Os in a row wins.")
	void forthRule(List<Position> positions, String expectedOutcomeMessage) {
		TicTacToeGame game = new TicTacToeGame();
		game.place(positions.toArray(new Position[0]));
		then(game).asString().endsWith(expectedOutcomeMessage);
	}

	private static Stream<Arguments> moves() {
		return Stream.of(
				arguments(asList(topLeft, bottomLeft, top, bottom, topRight), "First player won!"),
				arguments(asList(middle, topLeft, bottomLeft, top, bottom, topRight), "Second player won!"),
				arguments(asList(left, bottomLeft, middle, bottom, right), "First player won!"),
				arguments(asList(topLeft, top, topRight, bottomLeft, bottom, bottomRight, left, middle, right), "Draw!"));
	}
}
