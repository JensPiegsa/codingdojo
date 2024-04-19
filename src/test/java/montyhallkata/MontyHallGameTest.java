package montyhallkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class MontyHallGameTest {

    @Test @DisplayName("game model.")
    void gameModel() {

        int firstDoorSelection = 1;
        MontyHallGame montyHallGame = new MontyHallGame();

        String result = montyHallGame.openDoor(firstDoorSelection);
        then(result).isIn("Goat", "Prize");
    }

    

}