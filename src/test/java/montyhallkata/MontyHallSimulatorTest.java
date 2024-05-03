package montyhallkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("A MontyHallSimulator")
class MontyHallSimulatorTest {
    
    @Mock RandomNumberGenerator randomNumberGeneratorPrizeDoor;
    @Mock RandomNumberGenerator randomNumberGeneratorGameMaster;
    @Mock RandomNumberGenerator randomNumberGeneratorPlayerDoor;

    @ParameterizedTest
    @DisplayName("can run 1000 games")
    @CsvSource({"0, 0, 0, true, 1000",
            "0, 1, 0, true, 0",
            "0, 0, 0, false, 0",
            "0, 1, 0, false, 1000",
            "0, 1, 1, false, 1000",
    })
    void canRun1000Games(final int prizeDoor, final int playerDoor, final int gameMasterOption,
                         final boolean playerKeepsDoor, final int expectedWins) {

        final Integer[] prizeDoors = new Integer[999];
        Arrays.fill(prizeDoors, prizeDoor);
        final Integer[] gameMasterOptions = new Integer[999];
        Arrays.fill(gameMasterOptions, gameMasterOption);
        final Integer[] playerDoors = new Integer[999];
        Arrays.fill(playerDoors, playerDoor);
        given(randomNumberGeneratorPrizeDoor.nextInt(anyInt())).willReturn(prizeDoor, prizeDoors);
        given(randomNumberGeneratorPlayerDoor.nextInt(anyInt())).willReturn(playerDoor, playerDoors);
        given(randomNumberGeneratorGameMaster.nextInt(anyInt())).willReturn(gameMasterOption, gameMasterOptions);

        final var montyHallSimulator =
                new MontyHallSimulator(randomNumberGeneratorPrizeDoor, randomNumberGeneratorPlayerDoor, randomNumberGeneratorGameMaster);
        
        final SimulationResult result = montyHallSimulator.run(1000, playerKeepsDoor);
        
        then(result).isNotNull();
        then(result.getNumberOfWins()).isEqualTo(expectedWins);
    }
}
