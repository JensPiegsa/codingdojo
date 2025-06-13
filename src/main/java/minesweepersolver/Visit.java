package minesweepersolver;

public class Visit {

    private final Position position;

    public Visit(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
