package escapethemaze;

public enum Direction {
    east('>'), south('v'), west('<'), north('^');

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
}
