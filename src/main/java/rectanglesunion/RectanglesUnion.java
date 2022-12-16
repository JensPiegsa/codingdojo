package rectanglesunion;

import java.util.List;

public class RectanglesUnion {
    public static int calculateSpace(final int[][] rectangles) {
        int area = 0;
        for (final int[] rectangle : rectangles) {
            area += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
        }
        return area;
    }

    record Rectangle (int x1, int y1, int x2, int y2) {


        public boolean isInside(final Rectangle outerRectangle) {
            return x1 >= outerRectangle.x1 && x2 <= outerRectangle.x2
                    && y1 >= outerRectangle.y1 && y2 <= outerRectangle.y2;
        }

        public int getArea() {
            return Math.abs(x2 - x1) * Math.abs(y2 - y1);
        }

        public int getUnionArea(final Rectangle other) {
            if (this.isInside(other)) {
                return other.getArea();
            }
            if (other.isInside(this)) {
                return this.getArea();
            }
            return -1;
        }

        public boolean isDisjunction(final Rectangle other) {
            return false;
        }

        public boolean isInside(final Point p) {
            return x1 < p.x && p.x < x2
                    && y1 < p.y && p.y < y2;
        }

        public List<Point> getPoints() {
            return List.of(
                    new Point(x1, y1),
                    new Point(x1, y2),
                    new Point(x2, y1),
                    new Point(x2, y2)
            );
        }
    }
    
    record Point(int x, int y) {}
}