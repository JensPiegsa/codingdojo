package minesweepersolver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test @DisplayName("can count number of mines.")
    void canCountNumberOfMines() {
        Game.newGame("2 x\nx 2");

        int mines = Game.getMinesN();

        then(mines).isEqualTo(2);
    }

    @Test @DisplayName("can count number of mines 2.")
    void canCountNumberOfMines2() {
        Game.newGame("0 0\n0 0");

        int mines = Game.getMinesN();

        then(mines).isEqualTo(0);
    }
}