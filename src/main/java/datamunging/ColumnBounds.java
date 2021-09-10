package datamunging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ColumnBounds {

    Map<Integer, Integer> leftColumnBounds = new HashMap<>();
    Map<Integer, Integer> rightColumnBounds = new HashMap<>();

    public static ColumnBounds defineBounds(final int columnIndex, final int leftColumnBound, final int rightColumnBoundInclusive) {
        final ColumnBounds columnBounds = new ColumnBounds();
        return columnBounds.and(columnIndex, leftColumnBound, rightColumnBoundInclusive);
    }

    public ColumnBounds and(final int columnIndex, final int leftColumnBound, final int rightColumnBoundInclusive) {
        leftColumnBounds.put(columnIndex, leftColumnBound);
        rightColumnBounds.put(columnIndex, rightColumnBoundInclusive);

        return this;
    }

    public static ColumnBounds measure(final String headerLine) {

        final ColumnBounds columnBounds = new ColumnBounds();
        int currentColumnStartPosition = 0;
        int currentColumnIndex = 0;
        boolean previousCharWasSpace = false;
        boolean previousCharWasLeadingSpace = true;

        for (int charPosition = 0; charPosition < headerLine.length(); charPosition++) {
            boolean currentCharIsSpace = headerLine.charAt(charPosition) == ' ';
            boolean columnExceeded = !previousCharWasLeadingSpace && previousCharWasSpace && !currentCharIsSpace;
            if (columnExceeded) {
                columnBounds.and(currentColumnIndex, currentColumnStartPosition, charPosition - 1);
                currentColumnStartPosition = charPosition;
                currentColumnIndex++;
            }
            previousCharWasLeadingSpace = currentCharIsSpace && previousCharWasLeadingSpace;
            previousCharWasSpace = headerLine.charAt(charPosition) == ' ';
        }
        if (!headerLine.isEmpty()) {
            columnBounds.and(currentColumnIndex, currentColumnStartPosition, headerLine.length() - 1);
        }
        return columnBounds;
    }

    public ColumnBounds merge(final ColumnBounds customColumnBounds) {
        this.leftColumnBounds.putAll(customColumnBounds.leftColumnBounds);
        this.rightColumnBounds.putAll(customColumnBounds.rightColumnBounds);
        return this;
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
        for (final int columnIndex : leftColumnBounds.keySet()) {
            final int leftColumnBound = leftColumnBounds.get(columnIndex);
            final int rightColumnBound = rightColumnBounds.get(columnIndex);
            lines += "-".repeat(rightColumnBound - leftColumnBound - 2);
        }
        return header + "\n" + lines;
    }

    public String[] cut(final String line) {

        String[] result = new String[leftColumnBounds.size()];

        for (int columnIndex = 0; columnIndex < leftColumnBounds.size(); columnIndex++) {
            int beginIndex = leftColumnBounds.get(columnIndex);
            int endIndexExclusive = rightColumnBounds.get(columnIndex) + 1;
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
