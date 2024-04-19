package montyhallkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class MontyHallGameTest {

    @Test @DisplayName("play game player keeps selection")
    void playGamePlayerKeepsSelection() {

        int firstDoorSelection = 1;
        MontyHallGame montyHallGame = new MontyHallGame();

        montyHallGame.playersFirstSelection(firstDoorSelection);

        int openDoor = montyHallGame.gameMasterOpensDoor();

        then(openDoor).isNotEqualTo(firstDoorSelection);

        boolean playerWins = montyHallGame.playerKeepsSelection();
    }

    @Test @DisplayName("play game player switches selection")
    void playGamePlayerSwitchesSelection() {

        int firstDoorSelection = 1;
        MontyHallGame montyHallGame = new MontyHallGame();

        montyHallGame.playersFirstSelection(firstDoorSelection);

        int openDoor = montyHallGame.gameMasterOpensDoor();

        then(openDoor).isNotEqualTo(firstDoorSelection);

        boolean playerWins = montyHallGame.playerSwitchesSelection();
    }

    @Test @DisplayName("select impossible door.")
    void selectImpossibleDoor() {
        int firstDoorSelection = 3;
        MontyHallGame montyHallGame = new MontyHallGame();

        montyHallGame.playersFirstSelection(firstDoorSelection);
    }

}