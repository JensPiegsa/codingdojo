package montyhallkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

class MontyHallGameTest {

    @Test @DisplayName("play game player keeps selection")
    void playGamePlayerKeepsSelection() {

        final int firstDoorSelection = 1;
        final MontyHallGame montyHallGame = new MontyHallGame(new RandomNumberGenerator());

        montyHallGame.playersFirstSelection(firstDoorSelection);

        final int openDoor = montyHallGame.gameMasterOpensDoor();

        then(openDoor).isNotEqualTo(firstDoorSelection);

        final boolean playerWins = montyHallGame.playerKeepsSelection();
    }

    @Test @DisplayName("play game player switches selection")
    void playGamePlayerSwitchesSelection() {

        final int firstDoorSelection = 1;
        final MontyHallGame montyHallGame = new MontyHallGame(new RandomNumberGenerator());

        montyHallGame.playersFirstSelection(firstDoorSelection);

        final int openDoor = montyHallGame.gameMasterOpensDoor();

        then(openDoor).isNotEqualTo(firstDoorSelection);

        final boolean playerWins = montyHallGame.playerSwitchesSelection();
    }
    
    @Test @DisplayName("test")
    void test() {
        RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);
        final MontyHallGame game = new MontyHallGame(randomNumberGenerator);
        given(randomNumberGenerator.nextInt(anyInt())).willReturn(0);
        game.playersFirstSelection(1);
    }
}