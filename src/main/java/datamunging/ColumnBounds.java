package datamunging;

import java.util.HashMap;
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

	public static ColumnBounds measure(final String header) {

		final ColumnBounds columnBounds = new ColumnBounds();
		int columnStartPosition = 0;
		int columnIndex = 0;
		boolean lastCharSpace = false;

		for (int charPosition = 0; charPosition <= header.length(); charPosition++) {
			if ((charPosition == header.length()) || (lastCharSpace && header.charAt(charPosition) != ' ')) {
				columnBounds.and(columnIndex, columnStartPosition, charPosition-1);
				columnStartPosition = charPosition;
				columnIndex++;
			}
			if (charPosition < header.length()) {
				lastCharSpace = header.charAt(charPosition) == ' ';
			}
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
		return "ColumnBounds{" +
				"leftColumnBounds=" + leftColumnBounds +
				", rightColumnBounds=" + rightColumnBounds +
				'}';
	}
}
