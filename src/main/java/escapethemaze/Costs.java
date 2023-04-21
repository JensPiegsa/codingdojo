package escapethemaze;

import java.util.Arrays;

public record Costs(int[][] values) {

    public Costs(final Dimension dimension) {
        this(fill(new int[dimension.height()][dimension.width()]));
    }

    public void setValue(final Position position, final int value) {
        values[position.y()][position.x()] = value;
    }

    private static int[][] fill(final int[][] values) {
        for (final int[] row : values) {
            Arrays.fill(row, -1);
        }
        return values;
    }
}
