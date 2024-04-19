package montyhallkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class MontyHallSimulatorTest {
    @Test
    @DisplayName("can run 1000 games")
    void canRun1000Games() {
        MontyHallSimulator montyHallSimulator = new MontyHallSimulator();

        SimulationResult result = montyHallSimulator.run(1000);

        then(result).isNot(null);
    }
}
