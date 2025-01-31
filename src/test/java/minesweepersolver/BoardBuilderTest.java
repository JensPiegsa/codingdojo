package minesweepersolver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardBuilderTest {
    @Test @DisplayName("Create board")
    void createBoard() {
        BoardBuilder builder = BoardBuilder.ofSize(5, 5)
                .withMineAt(1, 2)
                .withMineAt(1, 3)
                .uncoveredAt(1, 2)
                .uncoveredAt(2, 2)
                .uncoveredAt(3, 4);

        Board boardUncovered = builder.getUncovered();
        assertThat(boardUncovered).isEqualTo(new Board("""
                0 1 2 2 1
                0 1 x x 1
                0 1 2 2 1
                0 0 0 0 0
                0 0 0 0 0
                """));


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