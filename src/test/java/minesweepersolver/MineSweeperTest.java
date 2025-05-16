package minesweepersolver;

import static org.assertj.core.api.BDDAssertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

        try (MockedStatic<Game> game = Mockito.mockStatic(Game.class)) {
            game.when(() -> Game.open(coveredRow, coveredCol)).thenReturn(0);

            BoardBuilder builder = BoardBuilder.ofSizeUncovered(5, 5)
                    .coveredAt(coveredRow, coveredCol);

            Board boardUncovered = builder.getUncovered();

            final String initialBoard = builder.getCovered().toString();
            final String solvedBoard = new MineSweeper(initialBoard, 2).solve();
            assertThat(solvedBoard).isEqualTo(boardUncovered.toString());
        }
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
    @CsvSource({"4,1,0", "0,2,0","3,0,0","1,4,0", // Ränder
               "0,0,0","0,4,0","4,0,0","4,4,0", // Ecken
               "3,1,1",    // 1er Ring
               "2,2,-1",    // mark mine intern
    })
    void solveReturnsWithMine(final int coveredRow, final int coveredCol, int returnCellValue) {

        try (MockedStatic<Game> game = Mockito.mockStatic(Game.class)) {
            game.when(() -> Game.open(coveredRow, coveredCol)).thenReturn(returnCellValue);

            BoardBuilder builder = BoardBuilder.ofSizeUncovered(5, 5)
                    .withMineAt(2, 2)
                    .coveredAt(coveredRow, coveredCol);

            Board boardUncovered = builder.getUncovered();

            final String initialBoard = builder.getCovered().toString();
            final String solvedBoard = new MineSweeper(initialBoard, 1).solve();
            assertThat(solvedBoard).isEqualTo(boardUncovered.toString());
        }
    }

    @Test @DisplayName("can mark Mine on board.")
    void canMarkMineOnBoard() {
        BoardBuilder builder = BoardBuilder.ofSizeUncovered(1, 1)
                .withMineAt(0, 0)
                .coveredAt(0, 0);

        Board boardUncovered = builder.getUncovered();

        final String initialBoard = builder.getCovered().toString();
        final String solvedBoard = new MineSweeper(initialBoard, 1).solve();
        assertThat(solvedBoard).isEqualTo(boardUncovered.toString());
    }

    @ParameterizedTest
    @CsvSource({
            "? ?\\nx 2,   2,   1",
            "? x\\nx 2,   2,   0"
    })
    void canCountRemainingHiddenMines(String boardStr, int mineCount, int expectedCount) {
        boardStr = boardStr.replace("\\n", "\n");
        MineSweeper mineSweeper = new MineSweeper(boardStr, mineCount);

        then(mineSweeper.getRemainingHiddenMineCount()).isEqualTo(expectedCount);
    }

    @ParameterizedTest
    @CsvSource({
            "? ?\\nx 2,   2,    2",
            "? x\\nx 2,   2,    1"
    })
    void canCountCoveredCells(String boardStr, int mineCount, int expectedCount) {
        boardStr = boardStr.replace("\\n", "\n");
        MineSweeper mineSweeper = new MineSweeper(boardStr, mineCount);

        then(mineSweeper.getRemainingCoveredCellCount()).isEqualTo(expectedCount);
    }

    
    /**
     *   Input:
     *   ? 1
     *   ? 1
     *   Expected solution:
     *   ? 
     */
    @ParameterizedTest
    @CsvSource({
            "? 1\\n? 1,   1"
    })
    @DisplayName("can not solve problem.")
    void canNotSolveProblem(String boardStr, int mineCount) {
        boardStr = boardStr.replace("\\n", "\n");
        MineSweeper mineSweeper = new MineSweeper(boardStr, mineCount);
        final String solvedBoard = mineSweeper.solve();
        assertThat(solvedBoard).isEqualTo("?");
    }

    @ParameterizedTest
    @CsvSource({
            "? 1\\n? 1,   2, x 1\\nx 1",
            "? 1 1\\n? 1 x,   1, 0 1 1\\n0 1 x"
    })
    @DisplayName("can solve complex problem.")
    void canSolveComplexProblem(String initialBoard, int mineCount, String expectedSolvedBoard) {
        initialBoard = initialBoard.replace("\\n", "\n");
        expectedSolvedBoard = expectedSolvedBoard.replace("\\n", "\n");
        Game.newGame(initialBoard);
        Game.read(expectedSolvedBoard);
        MineSweeper mineSweeper = new MineSweeper(initialBoard, mineCount);

        final String actualSolvedBoard = mineSweeper.solve();

        assertThat(actualSolvedBoard).isEqualTo(expectedSolvedBoard);
    }
}