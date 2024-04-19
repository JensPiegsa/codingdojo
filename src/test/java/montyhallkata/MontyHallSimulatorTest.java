package montyhallkata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

@DisplayName("A MontyHallSimulator")
class MontyHallSimulatorTest {
    
    @Test @DisplayName("can run 1000 games")
    void canRun1000Games() {
        final MontyHallSimulator montyHallSimulator = new MontyHallSimulator();
        final SimulationResult result = montyHallSimulator.run(1000);
        then(result).isNotNull();
    }
}
