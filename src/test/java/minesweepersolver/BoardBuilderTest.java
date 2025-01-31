package minesweepersolver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardBuilderTest {

    BoardBuilder builder;

    @BeforeEach
    void setUp() {
        builder = BoardBuilder.ofSize(5, 5)
                .withMineAt(1, 2)
                .withMineAt(1, 3)
                .uncoveredAt(1, 2)
                .uncoveredAt(2, 2)
                .uncoveredAt(3, 4);
    }

    @Test @DisplayName("Create uncovered board")
    void createUncoveredBoard() {
        Board boardUncovered = builder.getUncovered();
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
        Board boardCovered = builder.getCovered();
        assertThat(boardCovered).isEqualTo(new Board("""
                ? ? ? ? ?
                ? ? x ? ?
                ? ? 2 ? ?
                ? ? ? ? 0
                ? ? ? ? ?
                """));

    }
}