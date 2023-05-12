package escapethemaze;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class MazePath {

    private final List<Position> positions;

    @SuppressWarnings("unused")
    public static MazePath fromString(final String s) {
        if ("_".equals(s)) {
            return new MazePath();
        }
        
        final String[] split = s.split(" ");

        final List<Position> p = Arrays.stream(split)
                .filter(StringUtils::isNotBlank)
                .map(Position::fromString)
                .toList();
        
        return new MazePath(p);
    }
    
    public MazePath() {
        this(emptyList());
    }

    public MazePath(final List<Position> positions) {
        this.positions = new ArrayList<>(positions);
    }

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

    public MazePath reversed() {
        final var reversed = new ArrayList<>(positions);
        Collections.reverse(reversed);
        return new MazePath(reversed);
    }

    public int length() {
        return positions.size();
    }
}
