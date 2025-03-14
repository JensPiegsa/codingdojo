package minesweepersolver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class BoundsTest {

    @Test @DisplayName("checks position inside.")
    void checksPositionInside() {
        Bounds bounds = new Bounds(1, 2, 3, 4);
        Position position = Position.of(2, 2);

        boolean isInside = bounds.isInside(position);

        then(isInside).isTrue();
    }

    @Test @DisplayName("checks position outside.")
    void checksPositionOutside() {
        Bounds bounds = new Bounds(1, 2, 3, 4);
        Position position = Position.of(0, 0);

        boolean isInside = bounds.isInside(position);

        then(isInside).isFalse();
    }
}
