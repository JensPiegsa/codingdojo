package marsrover;

public enum Direction {

    NORTH (0, 1),
    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1);

    private int deltaX;
    private int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public Position moveForward(Position from) {
        return Position.of(from.getX() + deltaX, from.getY() + deltaY);
    }
}
