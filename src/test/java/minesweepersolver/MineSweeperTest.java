package minesweepersolver;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Jens Piegsa
 */
@DisplayName("A MineSweeper")
class MineSweeperTest {

    @Test
    void solveReturnsInitiallyFullySolvedBoard() {
        final String initialBoard = "2 x\nx 2";
        final String solvedBoard = new MineSweeper(initialBoard, 2).solve();
        assertThat(solvedBoard).isEqualTo(initialBoard);
    }
    
    @Test @DisplayName("solve() returns ? for fully covered board.")
    void solveReturnsForFullyCoveredBoard() {
    	final String initialBoard = "? ?\n? ?";
        final String solvedBoard = new MineSweeper(initialBoard, 2).solve();
        assertThat(solvedBoard).isEqualTo("?");
    }

    /**
     *   Input:
     *   0 1 2 2 1
     *   0 1 x x 1
     *   0 1 2 2 1
     *   0 0 0 0 0
     *   0 ? 0 0 0
     *   Expected solution:
     *   0 1 2 2 1
     *   0 1 x x 1
     *   0 1 2 2 1
     *   0 0 0 0 0
     *   0 0 0 0 0
     */
    @Test
    void solveReturns() {
        BoardBuilder builder = BoardBuilder.ofSizeUncovered(5, 5)
                .withMineAt(1, 2)
                .withMineAt(1, 3)
                .coveredAt(4, 1);

        Board boardUncovered = builder.getUncovered();

        final String initialBoard = builder.getCovered().toString();
        final String solvedBoard = new MineSweeper(initialBoard, 2).solve();
        assertThat(solvedBoard).isEqualTo(boardUncovered.toString());
    }
}