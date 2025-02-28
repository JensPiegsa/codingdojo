package minesweepersolver;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
     *   0 ? 0 0 0  "0,1"
     *   0 0 0 0 0
     *   0 0 0 0 0
     *   0 0 0 0 0
     *   0 ? 0 0 0  "4,1"
     *   Expected solution:
     *   0 0 0 0 0
     *   0 0 0 0 0
     *   0 0 0 0 0
     *   0 0 0 0 0
     *   0 0 0 0 0
     */
    @ParameterizedTest
    @CsvSource({"4,1","0,1",
                "1,4","1,0",
                "2,2",
                "0,0","0,4","4,0","4,4"})

    void solveReturnsWithoutMine(final int coveredRow, final int coveredCol) {
        BoardBuilder builder = BoardBuilder.ofSizeUncovered(5, 5)
                .coveredAt(coveredRow, coveredCol);

        Board boardUncovered = builder.getUncovered();

        final String initialBoard = builder.getCovered().toString();
        final String solvedBoard = new MineSweeper(initialBoard, 2).solve();
        assertThat(solvedBoard).isEqualTo(boardUncovered.toString());
    }

    /**
     *   Input:
     *   0 0 0 0 0
     *   0 1 1 1 0
     *   0 1 x 1 0
     *   0 1 1 1 0
     *   0 ? 0 0 0
     *   Expected solution:
     *   0 0 0 0 0
     *   0 1 1 1 0
     *   0 1 x 1 0
     *   0 1 1 1 0
     *   0 0 0 0 0
     */
    @ParameterizedTest
    @CsvSource({"4,1", "0,2","3,0","1,4", // Ränder
               "0,0","0,4","4,0","4,4", // Ecken
               "3,1"    // 1er Ring
    })
    void solveReturnsWithMine(final int coveredRow, final int coveredCol) {
        BoardBuilder builder = BoardBuilder.ofSizeUncovered(5, 5)
                .withMineAt(2, 2)
                .coveredAt(coveredRow, coveredCol);

        Board boardUncovered = builder.getUncovered();

        final String initialBoard = builder.getCovered().toString();
        final String solvedBoard = new MineSweeper(initialBoard, 2).solve();
        assertThat(solvedBoard).isEqualTo(boardUncovered.toString());
    }


}