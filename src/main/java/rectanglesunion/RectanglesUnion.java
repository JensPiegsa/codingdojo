package rectanglesunion;

public class RectanglesUnion {
    public static int calculateSpace(int[][] rectangles) {
        int area = 0;
        for (int[] rectangle : rectangles) {
            area += (rectangle[2] - rectangle[0]) * (rectangle[3] - rectangle[1]);
        }
        return area;
    }
}