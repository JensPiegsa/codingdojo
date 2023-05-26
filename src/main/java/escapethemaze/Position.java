package escapethemaze;

import java.util.List;
import java.util.stream.Stream;

public record Position(int x, int y) {
    public static Position of(final int x, final int y) {
        return new Position(x, y);
    }

    public Stream<Position> neighboursStream() {
        return Stream.of(
            of(x + 1, y),
            of(x - 1, y),
            of(x, y + 1),
            of(x, y - 1));
    }

    public List<Position> neighbours() {
        return neighboursStream().toList();
    }

    public static Position fromString(final String s) {
        final String[] split = s.split(",");
        return of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public Direction directionTowards(final Position other) {
        return Direction.fromDelta(other.x - x, other.y - y);
    }
}
