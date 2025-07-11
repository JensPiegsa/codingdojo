package minesweepersolver;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Jens Piegsa
 */
@DisplayName("A Board")
class BoardTest {

    @Test @DisplayName("has String representation of uncovered board.")
    void hasStringRepresentationOfUncoveredBoard() {
        final String board = "? ?\n? ?";
        final String string = new Board(board).toString();
        then(string).isEqualTo(board);
    }
    
    @Test @DisplayName("has String representation of fully covered board.")
    void hasStringRepresentationOfFullyCoveredBoard() {
        final String board = "2 x\nx 2";
        final String string = new Board(board).toString();
        then(string).isEqualTo(board);
    }

    @Test @DisplayName("minimal board of size 1 without mines")
    void minimalBoardOfSize1WithoutMines() {
    }

    @Test @DisplayName("minimal board of size 2 without mines")
    void minimalBoardOfSize2WithoutMines() {
    }

    @Test @DisplayName("minimal board of size 1 with mines only")
    void minimalBoardOfSize1WithMinesOnly() {
    }

    @Test @DisplayName("minimal board of size 2 with mines only")
    void minimalBoardOfSize2WithMinesOnly() {
    }

    @Test @DisplayName("minimal board of size 2 with mine")
    void minimalBoardOfSize2WithMine() {
    }

    @Test @DisplayName("Compare equal boards")
    void compareEqualBoards() {
        Board board1 = new Board("2 x\nx 2");
        Board board2 = new Board("2 x\nx 2");

        assertThat(board1).isEqualTo(board2);
    }

    @Test @DisplayName("Compare unequal boards")
    void compareUnequalBoards() {
        Board board1 = new Board("2 x\nx 2");
        Board board2 = new Board("2 x\nx ?");

        assertThat(board1).isNotEqualTo(board2);
    }

    @Test @DisplayName("Can return bounds")
    void canReturnBounds() {
        Board board = new Board("2 x\nx 3\nx 2");

        assertThat(board.getRows()).isEqualTo(3);
        assertThat(board.getColumns()).isEqualTo(2);
    }

    @Test @DisplayName("Can count mines for given point.")
    void canCountMines() {
        Board board = new Board("1 x\n? 1");

        int minesCount = board.countMinesAroundFor(new Position(1, 0));

        assertThat(minesCount).isEqualTo(1);
    }

    @Test @DisplayName("can check neighbour values.")
    void canCheckNeighbourValues() {
        Board board = new Board("1 x\nx 2");
        Position position = Position.of(1, 1);

        boolean value = board.hasNeighbourValue(position, -1);

        then(value).isTrue();
    }

    @Test @DisplayName("can check neighbour value 2.")
    void canCheckNeighbourValue2() {
        Board board = new Board("1 x\nx 2");
        Position position = Position.of(1, 1);

        boolean value = board.hasNeighbourValue(position, 0);

        then(value).isFalse();
    }

    @ParameterizedTest
    @DisplayName("can count marked mines.")
    @CsvSource({
            "? ?\\nx 2,  1",
            "? x\\nx 2,  2"
    })
    void canCountMarkedMines(String boardStr, int result) {
        Board board = new Board(boardStr.replace("\\n", "\n"));

        then(board.countMarkedMines()).isEqualTo(result);
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

    @Test @DisplayName("can count covered neighbours.")
    void canCountCoveredNeighbours() {
        Board board = new Board("""
                                    0 0 0
                                    ? 1 1
                                    ? 1 x
                                    """);
        Position position = Position.of(1,1);

        int result = board.countCoveredNeighbours(position);

        then(result).isEqualTo(2);
    }

    @Test @DisplayName("can count neighbouring mines.")
    void canCountNeighbouringMines() {
        Board board = new Board("""
                                    0 0 0
                                    ? 1 1
                                    ? 1 x
                                    """);
        Position position = Position.of(1,1);

        int result = board.countMinesAroundFor(position);

        then(result).isEqualTo(1);
    }
}
