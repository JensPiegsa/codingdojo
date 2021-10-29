package datamunging;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Table {

    private static final Logger log = Logger.getLogger(Table.class.getName());

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

    public String findMaximum(final int firstColumnIndex, final int secondColumnIndex,
                              final BiFunction<Double, Double, Double> function, final int resultColumnIndex) {
        Double maximum = null;
        int maximumRowIndex = -1;

        for (int rowIndex = 0; rowIndex < tableCells.length; rowIndex++) {
            final String[] row = tableCells[rowIndex];
            final String firstCell = row[firstColumnIndex];
            final String secondCell = row[secondColumnIndex];

            final Double firstCellValue = parseDoubleWeakly(firstCell, rowIndex, firstColumnIndex);
            final Double secondCellValue = parseDoubleWeakly(secondCell, rowIndex, secondColumnIndex);

            if (firstCellValue == null || secondCellValue == null) {
                continue;
            }
            final double rowResult = function.apply(firstCellValue, secondCellValue);

            if (maximum == null || rowResult > maximum) {
                maximum = rowResult;
                maximumRowIndex = rowIndex;
            }
        }
        return tableCells[maximumRowIndex][resultColumnIndex];
    }

    public String findMinimum(final int firstColumnIndex, final int secondColumnIndex,
                              final BiFunction<Double, Double, Double> function, final int resultColumnIndex) {
        Double minimum = null;
        int minimumRowIndex = -1;

        for (int rowIndex = 0; rowIndex < tableCells.length; rowIndex++) {
            final String[] row = tableCells[rowIndex];
            final String firstCell = row[firstColumnIndex];
            final String secondCell = row[secondColumnIndex];

            final Double firstCellValue = parseDoubleWeakly(firstCell, rowIndex, firstColumnIndex);
            final Double secondCellValue = parseDoubleWeakly(secondCell, rowIndex, secondColumnIndex);
            
            if (firstCellValue == null || secondCellValue == null) {
                continue;
            }
            final double rowResult = function.apply(firstCellValue, secondCellValue);

            if (minimum == null || rowResult < minimum) {
                minimum = rowResult;
                minimumRowIndex = rowIndex;
            }
        }
        return tableCells[minimumRowIndex][resultColumnIndex];
    }

    private Double parseDoubleWeakly(final String cell, final int rowIndex, final int columnIndex) {
        try {
            final Pattern pattern = Pattern.compile("([0-9]+|[0-9]+\\.[0-9]*|[0-9]*\\.[0-9]+)");

            final Matcher matcher = pattern.matcher(cell);
            final String number = matcher.find() ? matcher.group() : "";
            return Double.parseDouble(number);
        } catch (final NumberFormatException e) {
            log.warning(() -> "Number format exception at [" + rowIndex + ", "
                    + columnIndex + "] : " + e.getMessage());
            return null;
        }
    }

    public int getRowCount() {
        return tableCells.length;
    }

    public String getCellValue(final int rowIndex, final int columnIndex) {
        return tableCells[rowIndex][columnIndex];
    }
}

