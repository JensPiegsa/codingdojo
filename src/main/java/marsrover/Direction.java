package marsrover;

public enum Direction {

    NORTH(0, 1),
    WEST(-1, 0),
    SOUTH(0, -1),
    EAST(1, 0);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public Position moveForward(Position from) {
        return Position.of(from.getX() + deltaX, from.getY() + deltaY);
    }

    public Position moveBackwards(Position from) {
        return Position.of(from.getX() - deltaX, from.getY() - deltaY);
    }

    public Direction turnLeft() {
        final int newOrdinal = (ordinal() + 1) % values().length;
        return values()[newOrdinal];
    }
}
