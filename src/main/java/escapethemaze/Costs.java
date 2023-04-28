package escapethemaze;

import java.util.Arrays;

public record Costs(int[][] values, Dimension dimension) {

    public Costs(final Dimension dimension) {
        this(initializeRows(dimension), dimension);
    }

    public boolean isEmpty(final Position position) {
        return getValue(position) == -1;
    }

    public int getValue(final Position position) {
        return values[position.y()][position.x()];
    }

    public void setValue(final Position position, final int value) {
        values[position.y()][position.x()] = value;
    }

    private static int[][] initializeRows(final Dimension dimension) {
        return fill(new int[dimension.height()][dimension.width()]);
    }

    private static int[][] fill(final int[][] values) {
        for (final int[] row : values) {
            Arrays.fill(row, -1);
        }
        return values;
    }

    public void setMaximumValue(final Position position) {
        setValue(position, Integer.MAX_VALUE);
    }

    @Override
    public String toString() {
        final var out = new StringBuilder();
        for (int y = 0; y < dimension.height(); y++) {
            for (int x = 0; x < dimension.width(); x++) {
                final Position position = Position.of(x, y);
                out.append(getValue(position));
                out.append(' ');
            }
            out.append('\n');
        }
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Costs costs = (Costs) o;
        return Arrays.deepEquals(values, costs.values);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(values);
    }
}
