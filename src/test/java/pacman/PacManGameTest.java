package pacman;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PacMan")
public class PacManGameTest {

	// https://codingdojo.org/kata/PacMan/

	/**
	 *  PacMan
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
	 *     pacman is on a grid filled with dots
	 *     pacman has a direction
	 *     pacman moves on each tick
	 *     user can rotate pacman
	 *     pacman eats dots
	 *     pacman wraps around
	 *     pacman stops on wall
	 *     pacman will not rotate into a wall
	 *     game score (levels completed, number of dots eaten in this level)
	 *     monsters…
	 *     levels
	 *     animate pacman eating (mouth opens and closes)
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
			.V.\s\s
			. . .
        """);

		final String string = pacManGame.toString();

		assertThat(pacManGame.getBoardWidth()).isEqualTo(5);
		assertThat(pacManGame.getBoardHeight()).isEqualTo(3);
		// FIXME who removes our trailing white spaces at the end of second line:
		assertThat(string).isEqualTo("""
			. . .
			.V.\s\s
			. . .
        """);
	}
	
	@Test @DisplayName("can move up.")
	void canMoveUp() {
		
		final PacManGame pacManGame = new PacManGame("""
			. . .
			.V.  
			. . .
			""");
		pacManGame.move(Direction.up);

		assertThat(pacManGame.toString()).isEqualTo("""
			.V. .
			. .  
			. . .
        """);
	}
}
