package minesweepersolver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

public class PositionTest {

    /*
     *   Input:
     *   0 0 0 0
     *   0 0 ? 0
     *   0 0 0 0
     *   0 0 0 0
     */

    @Test @DisplayName("can list neighbours.")
    void canListNeighbours() {
        Position position = Position.of(1,2);

        Stream<Position> neighbours = position.getNeighbours(new Bounds(0,0,3,3));

        then(neighbours).containsExactlyInAnyOrder(Position.of(0, 1),
                Position.of(0, 2),
                Position.of(0, 3),
                Position.of(1, 1),
                Position.of(1, 3),
                Position.of(2, 1),
                Position.of(2, 2),
                Position.of(2, 3));
    }

    @Test @DisplayName("can list neighbours with bounds.")
    void canListNeighboursWithBounds() {
        Position position = Position.of(0,0);

        Stream<Position> neighbours = position.getNeighbours(new Bounds(0,0,0,0));

        then(neighbours).isEmpty();
    }
}
