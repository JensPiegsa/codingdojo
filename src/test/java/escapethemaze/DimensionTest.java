package escapethemaze;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("A Dimension")
class DimensionTest {

    @Test @DisplayName("returns true if position is in bounds.")
    void returnsTrueIfPositionIsInBounds() {
        final Dimension dimension = new Dimension(2, 4);
        final boolean inBounds = dimension.isInBounds(Position.of(1, 3));
        then(inBounds).isTrue();
    }
    
    @Test @DisplayName("returns false if position is out of bounds.")
    void returnsFalseIfPositionIsOutOfBounds() {
        final Dimension dimension = new Dimension(2, 4);
        final boolean inBounds = dimension.isInBounds(Position.of(10, 30));
        then(inBounds).isFalse();
    }

    @ParameterizedTest
    @DisplayName("isAtBorder() returns expected for position.")
    @CsvSource({
            "0, 0, true",
            "1, 0, true",
            "2, 0, true",
            "2, 2, true",
            "2, 3, true",
            "1, 3, true",
            "0, 3, true",
            "0, 2, true",
            "1, 2, false",
            "1, 1, false"})
    void testIsAtBorder(final int x, final int y, final boolean expected) {
        final Dimension dimension = new Dimension(3, 4);
        final boolean actual = dimension.isAtBorder(Position.of(x, y));
        then(actual).isEqualTo(expected);
    }
}