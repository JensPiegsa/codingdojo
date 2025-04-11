package minesweepersolver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardBuilderTest {

    BoardBuilder builderCovered;
    BoardBuilder builderUncovered;

    @BeforeEach
    void setUp() {
        builderCovered = BoardBuilder.ofSizeCovered(5, 5)
                .withMineAt(1, 2)
                .withMineAt(1, 3)
                .uncoveredAt(1, 2)
                .uncoveredAt(2, 2)
                .uncoveredAt(3, 4);
        builderUncovered = BoardBuilder.ofSizeUncovered(5, 5)
                .withMineAt(1, 2)
                .withMineAt(1, 3)
                .coveredAt(1, 2)
                .coveredAt(2, 2)
                .coveredAt(3, 4);
    }

    @Test @DisplayName("Create uncovered board")
    void createUncoveredBoard() {
        Board boardUncovered = builderCovered.getUncovered();
        assertThat(boardUncovered).isEqualTo(new Board("""
                0 1 2 2 1
                0 1 x x 1
                0 1 2 2 1
                0 0 0 0 0
                0 0 0 0 0
                """));

    }

    @Test @DisplayName("Create covered board")
    void createCoveredBoard() {
        Board boardCovered = builderCovered.getCovered();
        assertThat(boardCovered).isEqualTo(new Board("""
                ? ? ? ? ?
                ? ? x ? ?
                ? ? 2 ? ?
                ? ? ? ? 0
                ? ? ? ? ?
                """));

    }

    @Test @DisplayName("can set Mine at boarder.")
    void canSetMineAtBoarder() {
        Board boardCovered = BoardBuilder.ofSizeCovered(1, 1)
                .withMineAt(0, 0).getCovered();
        assertThat(boardCovered).isEqualTo(new Board("x"));
    }

//    @Test @DisplayName("Create uncovered board")
//    void createUncoveredBoard() {
//        Board boardUncovered = builderUncovered.getUncovered();
//        assertThat(boardUncovered).isEqualTo(new Board("""
//                0 1 2 2 1
//                0 1 x x 1
//                0 1 2 2 1
//                0 0 0 0 0
//                0 0 0 0 0
//                """));
//
//    }
//
//    @Test @DisplayName("Create covered board")
//    void createCoveredBoard() {
//        Board boardCovered = builderUncovered.getCovered();
//        assertThat(boardCovered).isEqualTo(new Board("""
//                ? ? ? ? ?
//                ? ? x ? ?
//                ? ? 2 ? ?
//                ? ? ? ? 0
//                ? ? ? ? ?
//                """));
//
//    }
}