package pacman;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("PacMan")
class PacManGameTest {

	// https://codingdojo.org/kata/PacMan/

	/**
	 * PacMan
	 * 
	 * About this Kata
	 * 
	 * Difficulty: easy
	 * 
	 * Good for teaching: does doing a SDUF (Small Design Up Front) taking monsters into account
	 * make a big difference in design and (initial and/or ongoing) velocity?
	 * 
	 * Related Katas: GameOfLife - the board design was taken from there. We simulate a game you
	 * play continuously with turns.
	 * 
	 * First performed January 6 during a devnology.nl event at Marktplaats.nl. Kicked off by
	 * Marc Evers and Rob Westgeest, initial steps brainstormed and facilitated by
	 * Willem van den Ende, programmed by a majority of the participants :)
	 * 
	 * Problem Description
	 * 
	 * Pacman finds himself in a grid filled with monsters. Will he be able to eat all the
	 * dots on the board before the monsters eat him?
	 * 
	 * Incomplete list of things the game needs:
	 * 
	 * [x] pacman is on a grid filled with dots
	 * [x] pacman has a direction
	 * [ ] pacman moves on each tick
	 * [ ] user can rotate pacman
	 * [ ] pacman eats dots
	 * [ ] pacman wraps around
	 * [ ] pacman stops on wall
	 * [ ] pacman will not rotate into a wall
	 * [ ] game score (levels completed, number of dots eaten in this level)
	 * monsters…
	 * levels
	 * animate pacman eating (mouth opens and closes)
	 * 
	 * Clues
	 * 
	 * You probably won’t be able to complete all of the list in one night of dojo, however
	 * having the list (or starting with part of it and letting the participants brainstorm)
	 * makes for good design discussions. As in the game of life, a board representation does not have to be difficult. E.g. pacman starts in the centre of the board and is looking up (notice that pacman eats, so the V points downward because pacman has his mouth open):
	 * 
	 * . . .
	 * .V.
	 * . . .
	 * 
	 * Pacman looks continuous, however the game state changes in discrete steps. Creating a
	 * tick() method/function or somesuch, or passing a board to a function which returns a
	 * ‘next state’ board makes it easy to test the various conditions.
	 * 
	 * Suggested Test Cases
	 * 
	 * see the pacman project on github.com (mostalive/pacman) for actual steps from the first run.
	 * 
	 * Comments from those who are working on this Kata
	 * 
	 * Feel free to leave your comments here. I hope you enjoy this kata. WillemVanDenEnde
	 */

	@Test @DisplayName("new game.")
	void newGame() {

		final PacManGame pacManGame = new PacManGame("""
				. . .
				.v.\s\s
				. . .
				""");

		final String string = pacManGame.getBoard();

		assertThat(pacManGame.getBoardWidth()).isEqualTo(5);
		assertThat(pacManGame.getBoardHeight()).isEqualTo(3);
		assertThat(string).isEqualTo("""
				. . .
				.v.\s\s
				. . .
				""");
	}

	@Nested @DisplayName("can move")
	class CanMove {

		@Test
		@DisplayName("can move up.")
		void canMoveUp() {

			final PacManGame pacManGame = new PacManGame("""
					. . .
					.v.\s\s
					. . .
					""");
			pacManGame.move(Direction.up);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					.v. .
					. .\s\s
					. . .
					""");
		}

		@Test @DisplayName("can move up cyclic.")
		void canMoveUpCyclic() {

			final PacManGame pacManGame = new PacManGame("""
					.v. .
					. .\s\s
					. . .
					""");
			pacManGame.move(Direction.up);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					. . .
					. .\s\s
					.v. .
					""");
		}

		@Test @DisplayName("can move down .")
		void canMoveDown() {

			final PacManGame pacManGame = new PacManGame("""
					.v. .
					. .\s\s
					. . .
					""");
			pacManGame.move(Direction.down);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					. . .
					.^.\s\s
					. . .
					""");
		}

		@Test @DisplayName("can move right .")
		void canMoveRight() {

			final PacManGame pacManGame = new PacManGame("""
					.v. .
					. .\s\s
					. . .
					""");
			pacManGame.move(Direction.right);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					. < .
					. .\s\s
					. . .
					""");
		}

		@Test @DisplayName("can move left .")
		void canMoveLeft() {

			final PacManGame pacManGame = new PacManGame("""
					.v. .
					. .\s\s
					. . .
					""");
			pacManGame.move(Direction.left);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					> . .
					. .\s\s
					. . .
					""");
		}

		@Test @DisplayName("can move down cyclic.")
		void canMoveDownCyclic() {

			final PacManGame pacManGame = new PacManGame("""
					. . .
					. .\s\s
					.v. .
					""");
			pacManGame.move(Direction.down);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					.^. .
					. .\s\s
					. . .
					""");
		}

		@Test @DisplayName("can move right cyclic.")
		void canMoveRightCyclic() {

			final PacManGame pacManGame = new PacManGame("""
					. . v
					. .\s\s
					. . .
					""");
			pacManGame.move(Direction.right);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					< .\s\s
					. .\s\s
					. . .
					""");
		}

		@Test @DisplayName("can move left cyclic.")
		void canMoveLeftCyclic() {

			final PacManGame pacManGame = new PacManGame("""
					v . .
					. .\s\s
					. . .
					""");
			pacManGame.move(Direction.left);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					  . >
					. .\s\s
					. . .
					""");
		}
	}
	
	@Nested @DisplayName("can change look direction")
	class CanChangeLookDirection {
		
		@Test @DisplayName("from the left.")
		void fromTheLeft() {
			final PacManGame pacManGame = new PacManGame("""
					>.
					..
					""");
			pacManGame.move(Direction.right);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					 <
					..
					""");
		}
		
		@Test @DisplayName("from the right.")
		void fromTheRight() {
			final PacManGame pacManGame = new PacManGame("""
					<.
					..
					""");
			pacManGame.move(Direction.right);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					 <
					..
					""");
		}
		
		@Test @DisplayName("from the top.")
		void fromTheTop() {
			final PacManGame pacManGame = new PacManGame("""
					v.
					..
					""");
			pacManGame.move(Direction.right);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					 <
					..
					""");
		}
		
		@Test @DisplayName("from the bottom.")
		void fromTheBottom() {
			final PacManGame pacManGame = new PacManGame("""
					^.
					..
					""");
			pacManGame.move(Direction.right);

			assertThat(pacManGame.getBoard()).isEqualTo("""
					 <
					..
					""");
		}
	}

	@Nested @DisplayName("has score")
	class HasScore {
		
		@Test @DisplayName("0 at start of game.")
		void zeroAtStartOfGame() {

			final PacManGame pacManGame = new PacManGame("""
					^.
					..
					""");

			final String screen = pacManGame.toString();
			
			assertThat(screen).isEqualTo("""
					^.
					..
					
					score: 0
					""");
		}

		@Test @DisplayName("0 after moving on empty tile.")
		void zeroAfterMovingOnEmptyTile() {

			final PacManGame pacManGame = new PacManGame("""
					^.
					 .
					""");

			pacManGame.move(Direction.down);
			final String screen = pacManGame.toString();

			assertThat(screen).isEqualTo("""
					 .
					^.
					
					score: 0
					""");
		}

		@Test @DisplayName("1 after eating point.")
		void oneAfterEatingPoint() {
			final PacManGame pacManGame = new PacManGame("""
					^.
					..
					""");

			pacManGame.move(Direction.right);
			
			assertThat(pacManGame.toString()).isEqualTo("""
					 <
					..
					
					score: 1
					""");
		}
		
		@Test @DisplayName("2 after eating two points.")
		void twoAfterEatingTwoPoints() {
			final PacManGame pacManGame = new PacManGame("""
					^.
					..
					""");

			pacManGame.move(Direction.right);
			pacManGame.move(Direction.down);

			assertThat(pacManGame.toString()).isEqualTo("""
					\s\s
					.^
					
					score: 2
					""");
		}
	}

	@Test @DisplayName("with terminal")
	void withTerminal() {
		// Startbildschirm: press Space
		// game loop

		final long tickDelayInMillis = 1000L;
		final PacManGame pacManGame = new PacManGame("""
				<.
				..
				""");

		final PacManGame expectedTickOneGame = new PacManGame("""
				 <
				..
				""");

		final TextGraphics textGraphics;
		try {
			final DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory(System.out, System.in, StandardCharsets.UTF_8);
			
			final Terminal terminal = terminalFactory.createTerminal();
			terminal.enterPrivateMode();

			terminal.clearScreen();

			printMultilineTextAtUpperLeftCorner(terminal, pacManGame.toString());
			terminal.flush();

			textGraphics = terminal.newTextGraphics();
			assertThat(textGraphics.getCharacter(0,0).getCharacterString()).isEqualTo("<");
			assertThat(textGraphics.getCharacter(1,0).getCharacterString()).isEqualTo(".");
			assertThat(textGraphics.getCharacter(0,1).getCharacterString()).isEqualTo(".");
			assertThat(textGraphics.getCharacter(1,1).getCharacterString()).isEqualTo(".");

			Thread.sleep((long) (tickDelayInMillis * 1.1f));
			terminal.setCursorPosition(0, 0);
			pacManGame.next();
			
			printMultilineTextAtUpperLeftCorner(terminal, pacManGame.toString());
			terminal.flush();

			assertThat(textGraphics.getCharacter(0,0).getCharacterString()).isEqualTo(" ");
			assertThat(textGraphics.getCharacter(1,0).getCharacterString()).isEqualTo("<");
			assertThat(textGraphics.getCharacter(0,1).getCharacterString()).isEqualTo(".");
			assertThat(textGraphics.getCharacter(1,1).getCharacterString()).isEqualTo(".");

		} catch (final IOException | InterruptedException e) {
			System.err.println("Fatal error: " + e.getMessage());
		}
	}
	
	

	private void printMultilineTextAtUpperLeftCorner(final Terminal terminal, final String string) throws IOException {
		int lineCounter = 0;
		for (final String line : string.split("[\n\r]")) {
			terminal.setCursorPosition(0, lineCounter);
			terminal.putString(line);
			lineCounter++;
		}
	}
}
