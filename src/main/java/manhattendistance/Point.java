package manhattendistance;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int distanceTo(final Point secondPoint) {
        return Math.abs(x - secondPoint.x) + Math.abs(y - secondPoint.y);
    }
}
