package tictactoe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

	@Test @DisplayName("o cannot go first-")
	void oCannotGoesFirst() {

		Game game = new Game();

		assertThrows(IllegalStateException.class, () -> game.place(Letter.O, new Point(0, 0)));
	}

	@Test @DisplayName("x always goes first")
	void xAlwaysGoesFirst() {

		Game game = new Game();

		try {
			game.place(Letter.X, new Point(0, 0));

		} catch (Exception e) {
			fail();
		}
	}

	@Test @DisplayName("cannot place x twice.")
	void cannotPlaceXTwice() {

		Game game = new Game();

		game.place(Letter.X, new Point(0, 0));
		assertThrows(IllegalStateException.class, () -> game.place(Letter.X, new Point(1, 0)));
	}


	@Test @DisplayName("cannot place o twice.")
	void cannotPlaceOTwice() {

		Game game = new Game();

		game.place(Letter.X, new Point(0, 0));
		game.place(Letter.O, new Point(1, 0));

		assertThrows(IllegalStateException.class, () -> game.place(Letter.O, new Point(0, 1)));
	}

	@Test @DisplayName("cannot place same position")
	void cannotPlaceSamePosition() {

		Game game = new Game();

		game.place(Letter.X, new Point(0, 0));
		assertThrows(IllegalArgumentException.class, () -> game.place(Letter.O, new Point(0, 0)));
	}

	@Test @DisplayName("cannont place outside Board")
	void cannontPlaceOutsideBoard() {

		Game game = new Game();

		assertThrows(ArrayIndexOutOfBoundsException.class, () -> game.place(Letter.X, new Point(3, 3)));
		
	}

	@Test @DisplayName("player x horizontal tripple")
	void playerXHorizontalTripple() {
		Game game = new Game();
		game.place(Letter.X, new Point(0, 0));
		game.place(Letter.O, new Point(0, 1));
		game.place(Letter.X, new Point(1, 0));
		game.place(Letter.O, new Point(1, 1));

		assertEquals(GameResult.X_Win, game.place(Letter.X, new Point(2, 0)));
	}

	@Test @DisplayName("player o vertical tripple")
	void playerOVerticalTripple() {
		Game game = new Game();
		game.place(Letter.X, new Point(0, 0));
		game.place(Letter.O, new Point(1, 0));
		game.place(Letter.X, new Point(2, 0));
		game.place(Letter.O, new Point(1, 1));
		game.place(Letter.X, new Point(2, 1));

		assertEquals(GameResult.O_Win, game.place(Letter.O, new Point(1, 2)));
	}

	@Test @DisplayName("player x diagonal")
	void playerXDiagonal() {
		Game game = new Game();
		game.place(Letter.X, new Point(0, 0));
		game.place(Letter.O, new Point(0, 1));
		game.place(Letter.X, new Point(1, 1));
		game.place(Letter.O, new Point(0, 2));

		assertEquals(GameResult.X_Win, game.place(Letter.X, new Point(2, 2)));
	}

	@Test @DisplayName("player x backwards diagonal")
	void playerXBackwardsDiagonal() {
		Game game = new Game();
		game.place(Letter.X, new Point(2, 0));
		game.place(Letter.O, new Point(0, 1));
		game.place(Letter.X, new Point(1, 1));
		game.place(Letter.O, new Point(1, 2));

		assertEquals(GameResult.X_Win, game.place(Letter.X, new Point(0, 2)));
	}

	@Test @DisplayName("print board")
	void printBoard() {
		// given
		Game game = new Game();
		game.place(Letter.X, new Point(2, 0));
		game.place(Letter.O, new Point(0, 1));
		game.place(Letter.X, new Point(1, 1));
		game.place(Letter.O, new Point(1, 2));

		String expected =
						"_ _ X\n" +
						"O X _\n" +
						"_ O _";
		// when

		// then
		assertThat(game.printBoard()).isEqualTo(expected);
	}
}