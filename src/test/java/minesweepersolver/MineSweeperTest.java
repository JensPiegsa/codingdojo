package minesweepersolver;

import static org.assertj.core.api.BDDAssertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.willReturn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.PriorityQueue;

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

    @Test @DisplayName("add priorityQueue")
    void addPriorityQueue() {
        String initialBoard = """
                              ? ? ? ? ? ? ?
                              ? ? 3 1 ? ? ?
                              ? x 1 0 ? ? ?
                              ? ? 1 0 0 1 ?
                              ? 1 1 1 0 0 ?
                              ? 1 x 1 0 0 ?
                              ? ? ? ? ? ? ?
                              """;
        /*
                              ? # # # # ? ?     ? = deep unknown
                              ? # + + # ? ?     # = unknown border -> queue
                              ? * + + # # #     + = known border -> queue
                              # # + + + + #     * = marked mine
                              # + + v v + #     v = deep known
                              # + * + + + #
                              # # # # # # #
         */
        MineSweeper mineSweeper = new MineSweeper(initialBoard, 5);
        PriorityQueue<Visit> visits = mineSweeper.initQueue();

        then(visits).map(Visit::getPosition).containsExactlyInAnyOrder(
                Position.of(0,1),
                Position.of(0,2),
                Position.of(0,3),
                Position.of(0,4),

                Position.of(1,1),
                Position.of(1,2),
                Position.of(1,3),
                Position.of(1,4),

                Position.of(2,2),
                Position.of(2,3),
                Position.of(2,4),
                Position.of(2,5),
                Position.of(2,6),

                Position.of(3,0),
                Position.of(3,1),
                Position.of(3,2),
                Position.of(3,3),
                Position.of(3,4),
                Position.of(3,5),
                Position.of(3,6),

                Position.of(4,0),
                Position.of(4,1),
                Position.of(4,2),
                Position.of(4,5),
                Position.of(4,6),

                Position.of(5,0),
                Position.of(5,1),
                Position.of(5,3),
                Position.of(5,4),
                Position.of(5,5),
                Position.of(5,6),

                Position.of(6,0),
                Position.of(6,1),
                Position.of(6,2),
                Position.of(6,3),
                Position.of(6,4),
                Position.of(6,5),
                Position.of(6,6)
                );
    }

    @Test
    @DisplayName("can detect if has unknown neighbors")
    void canDetectIfHasUnknownNeighbors() {
        String boardString = """
                0 0 0 0 ?
                0 1 1 1 0
                0 1 ? 1 0
                0 ? ? 1 0
                0 ? 0 0 0
                """;

        Board board = new Board(boardString);

        assertThat(board.hasUnknownNeighbour(Position.of(0,0))).isFalse();
        assertThat(board.hasUnknownNeighbour(Position.of(1,1))).isTrue();
        assertThat(board.hasUnknownNeighbour(Position.of(0,4))).isFalse();
        assertThat(board.hasUnknownNeighbour(Position.of(3,1))).isTrue();
    }

    @Test
    @DisplayName("can detect if has known neighbors")
    void canDetectIfHasKnownNeighbors() {
        String boardString = """
                0 0 0 ? 0
                0 1 1 ? ?
                0 1 ? 1 0
                ? ? ? 1 0
                ? ? ? 0 0
                """;

        Board board = new Board(boardString);

        assertThat(board.hasKnownNeighbourNumber(Position.of(0,0))).isTrue();
        assertThat(board.hasKnownNeighbourNumber(Position.of(1,1))).isTrue();
        assertThat(board.hasKnownNeighbourNumber(Position.of(0,4))).isFalse();
        assertThat(board.hasKnownNeighbourNumber(Position.of(4,1))).isFalse();
    }

    @Test
    @DisplayName("can detect if is unknown boarder")
    void canDetectIfIsUnknownBoarder() {
        String boardString = """
                0 0 0 0 ?
                0 1 1 1 0
                0 1 ? 1 0
                0 ? ? ? ?
                0 ? 0 ? ?
                """;

        Board board = new Board(boardString);

        assertThat(board.isUnknownBorder(Position.of(0,0))).isFalse();
        assertThat(board.isUnknownBorder(Position.of(1,1))).isFalse();
        assertThat(board.isUnknownBorder(Position.of(0,4))).isTrue();
        assertThat(board.isUnknownBorder(Position.of(3,1))).isTrue();
        assertThat(board.isUnknownBorder(Position.of(4,4))).isFalse();
    }

    @Test
    @DisplayName("can detect if is known boarder")
    void canDetectIfIsKnownBoarder() {
        String boardString = """
                0 0 0 0 ?
                0 1 ? 1 0
                0 1 x 1 0
                0 ? ? ? ?
                0 ? 0 ? ?
                """;

        Board board = new Board(boardString);

        assertThat(board.isKnownBorder(Position.of(0,0))).isFalse();
        assertThat(board.isKnownBorder(Position.of(1,1))).isTrue();
        assertThat(board.isKnownBorder(Position.of(0,4))).isFalse();
        assertThat(board.isKnownBorder(Position.of(3,1))).isFalse();
        assertThat(board.isKnownBorder(Position.of(4,4))).isFalse();
        assertThat(board.isKnownBorder(Position.of(2,2))).isFalse();
    }
}