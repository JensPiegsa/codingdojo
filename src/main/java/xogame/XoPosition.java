package xogame;

/**
 * @author Jens Piegsa
 */
public enum XoPosition {
    upperLeft (0,0),
    upperMiddle (0,1),
    upperRight (0, 2),
    centerLeft(1, 0),
    centerMiddle(1, 1),
    centerRight( 1, 2),
    lowerLeft(2,0),
    lowerMiddle(2,1),
    lowerRight(2,2);

    private final int x;
    private final int y;

    XoPosition(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
