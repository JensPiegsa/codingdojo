package minesweepersolver;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Visit implements Comparable<Visit> {

    private final Position position;
    private final int priority;

    public Visit(Position position, int priority) {
        this.position = position;
        this.priority = priority;
    }

    public Position getPosition() {
        return position;
    }


    @Override
    public int compareTo(@NotNull Visit o) {
        return Integer.compare(priority, o.priority);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return priority == visit.priority && Objects.equals(position, visit.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, priority);
    }

    @Override
    public String toString() {
        return "Visit{" +
               "position=" + position +
               ", priority=" + priority +
               '}';
    }
}
