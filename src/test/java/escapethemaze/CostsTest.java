package escapethemaze;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A Costs")
class CostsTest {

    @Test @DisplayName("allows to query empty position.")
    void testIsEmpty() {
        Costs costs = new Costs(new Dimension(1, 1));
        Position position = Position.of(0, 0);
        assertThat(costs.isEmpty(position)).isTrue();
        costs.setValue(position, 0);
        assertThat(costs.isEmpty(position)).isFalse();
    }
}