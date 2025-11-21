package minesweepersolver;

/**
 * @param left
 * @param top
 * @param right
 * @param bottom
 *
 * parameters inclusive
 */
public record Bounds(int left, int top, int right, int bottom) {

    public boolean isInside(Position position) {
        int col = position.col();
        int row = position.row();

        return col >= left && col <= right && row >= top && row <= bottom;
    }
}
