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
}
