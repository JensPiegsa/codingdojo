package minesweepersolver;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Board board1 = new Board("2 x\nx 3\nx 2");

        assertThat(board1.getRows()).isEqualTo(3);
        assertThat(board1.getColumns()).isEqualTo(2);
    }
}
