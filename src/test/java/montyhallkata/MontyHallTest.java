package montyhallkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MontyHallTest {

    @Test @DisplayName("game model.")
    void gameModel() {

        int firstDoorSelection = 1;
        MontyHallGame montyHallGame = new MontyHallGame();

        montyHallGame.openDoor(firstDoorSelection);
    }

}