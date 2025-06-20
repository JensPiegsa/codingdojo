package minesweepersolver;

import org.jetbrains.annotations.NotNull;

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
}
