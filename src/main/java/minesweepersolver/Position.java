package minesweepersolver;

import java.util.Objects;
import java.util.stream.Stream;

public record Position(int row, int col) {
    public static Position of(int row, int col) {
        return new Position(row, col);
    }

    public Stream<Position> getNeighbours(Bounds bounds) {
        return Stream.of(
                Position.of(row - 1, col - 1),
                Position.of(row - 1, col),
                Position.of(row - 1, col + 1),
                Position.of(row, col - 1),
                Position.of(row, col + 1),
                Position.of(row + 1, col - 1),
                Position.of(row + 1, col),
                Position.of(row + 1, col + 1))
                .filter(bounds::isInside);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
