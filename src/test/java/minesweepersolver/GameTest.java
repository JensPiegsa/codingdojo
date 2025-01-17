package minesweepersolver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test @DisplayName("can count number of mines.")
    void canCountNumberOfMines() {
        Game.newGame("2 x\nx 2");
        Game.read("2 x\nx 2");

        int mines = Game.getMinesN();

        then(mines).isEqualTo(2);
    }

    @Test @DisplayName("can count number of mines 2.")
    void canCountNumberOfMines2() {
        Game.newGame("0 0\n0 0");
        Game.read("0 0\n0 0");

        int mines = Game.getMinesN();

        then(mines).isEqualTo(0);
    }

    @Test @DisplayName("can click on mine.")
    void canClickOnMine() {
        Game.newGame("x");
        Game.read("x");

        int row = 0;
        int column = 0;

        try{
            int numberOfMines = Game.open(row, column);
            fail("Exception expected: mine not hit");
        } catch (IllegalStateException e) {
            then(e.getMessage()).isEqualTo("Mine hit at [0, 0]");
        }
    }

    @Test @DisplayName("can get number of surrounding mines.")
    void canGetNumberOfSurroundingMines() {
        Game.newGame("? x\n1 1");
        Game.read("1 x\n1 1");

        int row = 0;
        int column = 0;

        int numberOfMines = Game.open(row, column);
        then(numberOfMines).isEqualTo(1);
    }

    @Test @DisplayName("can get number of surrounding mines2.")
    void canGetNumberOfSurroundingMines2() {
        Game.newGame("2 x\nx ?");
        Game.read("2 x\nx 2");

        int row = 1;
        int column = 1;

        int numberOfMines = Game.open(row, column);
        then(numberOfMines).isEqualTo(2);
    }

    @Test @DisplayName("cannot click to uncovered field.")
    void cannotClickToUncoveredField() {
        Game.newGame("2 x\nx ?");
        Game.read("2 x\nx 2");

        int row = 0;
        int column = 0;

        try{
            int numberOfMines = Game.open(row, column);
        } catch (IllegalStateException e) {
            then(e.getMessage()).isEqualTo("Field already uncovered at [0, 0]");
        }
    }
}