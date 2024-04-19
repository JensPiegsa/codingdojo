package montyhallkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class MontyHallGameTest {

    @Test @DisplayName("game model.")
    void gameModel() {

        int firstDoorSelection = 1;
        MontyHallGame montyHallGame = new MontyHallGame();

        montyHallGame.selectDoor(firstDoorSelection);

        int selectedDoor = montyHallGame.openDoor();

        then(selectedDoor).isNotEqualTo(firstDoorSelection);
    }

    @Test @DisplayName("select impossible door.")
    void selectImpossibleDoor() {
        int firstDoorSelection = 3;
        MontyHallGame montyHallGame = new MontyHallGame();

        montyHallGame.selectDoor(firstDoorSelection);

    }

}