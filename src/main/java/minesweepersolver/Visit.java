package minesweepersolver;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Visit implements Comparable<Visit> {

    private final Position position;
    private final Strategy strategy;

    public Visit(Position position, Strategy strategy) {
        this.position = position;
        this.strategy = strategy;
    }

    public Position getPosition() {
        return position;
    }


    @Override
    public int compareTo(@NotNull Visit o) {
        return Integer.compare(o.strategy.getPriority(), strategy.getPriority());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return strategy.getPriority() == visit.strategy.getPriority()
                && Objects.equals(position, visit.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, strategy.getPriority());
    }

    @Override
    public String toString() {
        return "Visit{" +
               "position=" + position +
               ", strategy=" + strategy +
               '}';
    }
}
