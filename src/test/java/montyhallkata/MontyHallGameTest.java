package montyhallkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

class MontyHallGameTest {

    @Test @DisplayName("play game player keeps selection")
    void playGamePlayerKeepsSelection() {

        final int firstDoorSelection = 1;
        final MontyHallGame montyHallGame = new MontyHallGame(new RandomNumberGenerator(), new RandomNumberGenerator());

        montyHallGame.playersFirstSelection(firstDoorSelection);

        final int openDoor = montyHallGame.gameMasterOpensDoor();

        then(openDoor).isNotEqualTo(firstDoorSelection);

        final boolean playerWins = montyHallGame.playerKeepsSelection();
    }

    @Test @DisplayName("play game player switches selection")
    void playGamePlayerSwitchesSelection() {

        final int firstDoorSelection = 1;
        final MontyHallGame montyHallGame = new MontyHallGame(new RandomNumberGenerator(), new RandomNumberGenerator());

        montyHallGame.playersFirstSelection(firstDoorSelection);

        final int openDoor = montyHallGame.gameMasterOpensDoor();

        then(openDoor).isNotEqualTo(firstDoorSelection);

        final boolean playerWins = montyHallGame.playerSwitchesSelection();
    }
    
    @ParameterizedTest
    @DisplayName("method: playerKeepsDoor")
    @CsvSource({"0, 0, 0, true, true",
                "0, 1, 0, true, false",
                "0, 0, 0, false, false",
                "0, 1, 0, false, true",
                "0, 1, 1, false, true",
    })
    void test(int prizeDoor, int playerDoor, int gameMasterOption,
              boolean playerKeepsDoor, boolean expectedPrize) {

        RandomNumberGenerator randomNumberGeneratorPrizeDoor = mock(RandomNumberGenerator.class);
        RandomNumberGenerator randomNumberGeneratorGameMaster = mock(RandomNumberGenerator.class);
        final MontyHallGame game = new MontyHallGame(randomNumberGeneratorPrizeDoor, randomNumberGeneratorGameMaster);
        given(randomNumberGeneratorPrizeDoor.nextInt(anyInt())).willReturn(prizeDoor);
        given(randomNumberGeneratorGameMaster.nextInt(anyInt())).willReturn(gameMasterOption);

        game.playersFirstSelection(playerDoor);

        game.gameMasterOpensDoor();

        boolean result;
        if (playerKeepsDoor) {
            result = game.playerKeepsSelection();
        } else {
            result = game.playerSwitchesSelection();
        }

        then(result).isEqualTo(expectedPrize);
    }


}