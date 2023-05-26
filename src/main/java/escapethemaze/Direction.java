package escapethemaze;

public enum Direction {
    east('>'),  // 0
    south('v'), // 1
    west('<'),  // 2
    north('^'); // 3

    private final char character;

    Direction(final char character) {
        this.character = character;
    }

    public static Direction of(final char character) {
        for (final Direction direction : values()) {
            if (direction.character == character) {
                return direction;
            }
        }
        return null;
    }

    @SuppressWarnings("ReturnOfNull")
    public static Direction fromDelta(final int deltaX, final int deltaY) {
        if (deltaX < 0 && deltaY == 0) {
            return west;
        }
        if (deltaX > 0 && deltaY == 0) {
            return east;
        }
        if (deltaX == 0 && deltaY < 0) {
            return north;
        }
        if (deltaX == 0 && deltaY > 0) {
            return south;
        }
        return null;
    }

    public NavigationCommand turnTowards(final Direction requiredDirection) {
        if (requiredDirection == this) {
            return null;
        }
        final int directionDelta = (values().length + requiredDirection.ordinal() - ordinal()) % values().length;
        return NavigationCommand.values()[directionDelta];
    }
}
