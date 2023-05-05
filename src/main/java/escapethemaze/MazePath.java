package escapethemaze;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MazePath {

    private final List<Position> positions = new ArrayList<>();

    public MazePath add(final Position position) {
        positions.add(position);
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MazePath mazePath = (MazePath) o;
        return Objects.equals(positions, mazePath.positions);
    }

    @Override
    @SuppressWarnings("ObjectInstantiationInEqualsHashCode")
    public int hashCode() {
        return Objects.hash(positions);
    }
}
