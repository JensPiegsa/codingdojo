package christmaslightskata;

import java.util.Objects;

public final class Rectangle {
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    public static Rectangle between(Point topLeft, Point bottomRight) {
        return new Rectangle(topLeft,  bottomRight);
    }

    public Rectangle(Point topLeft, Point bottomRight) {
        this.x1 = topLeft.x();
        this.y1 = topLeft.y();
        this.x2 = bottomRight.x();
        this.y2 = bottomRight.y();
    }

    public int x1() {
        return x1;
    }

    public int y1() {
        return y1;
    }

    public int x2() {
        return x2;
    }

    public int y2() {
        return y2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Rectangle) obj;
        return this.x1 == that.x1 &&
                this.y1 == that.y1 &&
                this.x2 == that.x2 &&
                this.y2 == that.y2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2);
    }

    @Override
    public String toString() {
        return "Rectangle[" +
                "x1=" + x1 + ", " +
                "y1=" + y1 + ", " +
                "x2=" + x2 + ", " +
                "y2=" + y2 + ']';
    }

}