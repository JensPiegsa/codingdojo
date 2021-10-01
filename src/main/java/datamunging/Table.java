package datamunging;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Table {

    private final String[] head;
    private final String[][] tableCells;

    public Table(final String[] head, final String[][] tableCells) {
        this.head = head;
        this.tableCells = tableCells;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        final String header = Arrays.stream(head)
                .map(s -> s.isEmpty() ? "<NA>" : s)
                .collect(joining(", "));
        stringBuilder.append(header);
        for (final String[] line : tableCells) {
            stringBuilder.append("\n");
            final String lineString = Arrays.stream(line)
                    .map(s -> s.isEmpty() ? "" : s)
                    .collect(joining(", "));
            stringBuilder.append(lineString);
        }
        return stringBuilder.toString();
    }

    public <T extends Number> String findMinimum(final int firsColumnIndex, final int secondColumnIndex, 
                              final BiFunction<String, String, T> function, final int resultColumnIndex) {
        T minimum = null;
        for (final String[] row : tableCells) {
            final String firstCell = row[firsColumnIndex];
            final String secondCell = row[secondColumnIndex];
            final T rowResult = function.apply(firstCell, secondCell);
            if (minimum == null || rowResult < minimum) {
                
            }
        }
        return null;
    }
}

