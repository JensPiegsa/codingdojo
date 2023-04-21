package escapethemaze;

public record Dimension(int width, int height) {

    public boolean isInBounds(final Position position) {
        return width > 0 && height > 0
                && position.x() >= 0 && position.x() < width
                && position.y() >= 0 && position.y() < height;
    }
    
    public boolean isAtBorder(final Position position) {
        return position.x() == 0
                || position.x() == width - 1
                || position.y() == 0
                || position.y() == height - 1;
    }
}
