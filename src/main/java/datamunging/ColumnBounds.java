package datamunging;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ColumnBounds {

    private final Map<Integer, Integer> leftColumnBounds = new HashMap<>();
    private final Map<Integer, Integer> rightColumnBounds = new HashMap<>();

    public static ColumnBounds defineBounds(final int columnIndex, final int leftColumnBound, final int rightColumnBoundInclusive) {
        final ColumnBounds columnBounds = new ColumnBounds();
        return columnBounds.and(columnIndex, leftColumnBound, rightColumnBoundInclusive);
    }

    public ColumnBounds and(final int columnIndex, final int leftColumnBound, final int rightColumnBoundInclusive) {
        final ColumnBounds columnBounds = new ColumnBounds();
        columnBounds.leftColumnBounds.putAll(leftColumnBounds);
        columnBounds.rightColumnBounds.putAll(rightColumnBounds);
        columnBounds.leftColumnBounds.put(columnIndex, leftColumnBound);
        columnBounds.rightColumnBounds.put(columnIndex, rightColumnBoundInclusive);
        return columnBounds;
    }

    public static ColumnBounds measure(final String headerLine) {

        ColumnBounds columnBounds = new ColumnBounds();
        int currentColumnStartPosition = 0;
        int currentColumnIndex = 0;
        boolean previousCharWasSpace = false;
        boolean previousCharWasLeadingSpace = true;

        for (int charPosition = 0; charPosition < headerLine.length(); charPosition++) {
            final boolean currentCharIsSpace = headerLine.charAt(charPosition) == ' ';
            final boolean columnExceeded = !previousCharWasLeadingSpace && previousCharWasSpace && !currentCharIsSpace;
            if (columnExceeded) {
                columnBounds = columnBounds.and(currentColumnIndex, currentColumnStartPosition, charPosition - 1);
                currentColumnStartPosition = charPosition;
                currentColumnIndex++;
            }
            previousCharWasLeadingSpace = currentCharIsSpace && previousCharWasLeadingSpace;
            previousCharWasSpace = headerLine.charAt(charPosition) == ' ';
        }
        if (!headerLine.isEmpty()) {
            columnBounds = columnBounds.and(currentColumnIndex, currentColumnStartPosition, headerLine.length() - 1);
        }
        return columnBounds;
    }

    public ColumnBounds merge(final ColumnBounds customColumnBounds) {
        final ColumnBounds columnBounds = new ColumnBounds();
        
        columnBounds.leftColumnBounds.putAll(leftColumnBounds);
        columnBounds.rightColumnBounds.putAll(rightColumnBounds);
        columnBounds.leftColumnBounds.putAll(customColumnBounds.leftColumnBounds);
        columnBounds.rightColumnBounds.putAll(customColumnBounds.rightColumnBounds);
        
        return columnBounds;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ColumnBounds that = (ColumnBounds) o;
        return leftColumnBounds.equals(that.leftColumnBounds) && rightColumnBounds.equals(that.rightColumnBounds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftColumnBounds, rightColumnBounds);
    }

    @Override
    public String toString() {
        if (leftColumnBounds.isEmpty() && rightColumnBounds.isEmpty()) {
            return "ColumnBounds[empty]";
        }
        final String header = "ColumnBounds[" +
                "leftColumnBounds=" + leftColumnBounds +
                ", rightColumnBounds=" + rightColumnBounds +
                "]:";
        String lines = "";
        String indices = "";
        for (final int columnIndex : leftColumnBounds.keySet()) {
            final int leftColumnBound = leftColumnBounds.get(columnIndex);
            final int rightColumnBound = rightColumnBounds.get(columnIndex);
            final int columnWidth = rightColumnBound - leftColumnBound + 1;
            lines += "|";
            lines += "-".repeat(Math.max(0, columnWidth - 2));
            if (columnWidth > 1) {
                lines += "|";
            }

            indices += String.valueOf(columnIndex % 10).repeat(columnWidth);
        }
        return header + "\n" + lines + "\n" + indices;
    }

    public String[] cut(final String line) {

        final String[] result = new String[leftColumnBounds.size()];

        for (int columnIndex = 0; columnIndex < leftColumnBounds.size(); columnIndex++) {
            final int beginIndex = leftColumnBounds.get(columnIndex);
            final int endIndexExclusive = rightColumnBounds.get(columnIndex) + 1;
            if (beginIndex > line.length()) {
                result[columnIndex] = "";
            } else if (endIndexExclusive > line.length()) {
                result[columnIndex] = line.substring(beginIndex);
            } else {
                result[columnIndex] = line.substring(beginIndex, endIndexExclusive);
            }
        }

        return result;
    }
}
