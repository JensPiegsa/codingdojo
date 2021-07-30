package datamunging;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColumnBoundsTest {

	@Test @DisplayName("can measure and merge")
	void canMeasureAndMerge() {
		// given
		final String header = "MxR  MnR AvSLP BOB"; // measured| custom | custom | measured
		//                     |---||-||-----||-|
		//                     000001112222222333

		final ColumnBounds customColumnBounds = ColumnBounds
				.defineBounds(2, 8, 14)
				.and(1, 5, 7);

		// when
		ColumnBounds columnBounds = ColumnBounds.measure(header).merge(customColumnBounds);

		// then
		then(columnBounds).isEqualTo(
				ColumnBounds.defineBounds(0, 0, 4)
						.and(1, 5, 7)
						.and(2, 8, 14)
						.and(3,15,17));
	}

	@Test @DisplayName("can cut string.")
	void canCutString() {
		// given
		final ColumnBounds columnBounds = ColumnBounds.measure("AAA BB");

		// when
		String[] individualColumn = columnBounds.cut("aaa bb");

		// then
		then(individualColumn).containsExactly("aaa", "bb");
	}

	@Test @DisplayName("measure only")
	void measureOnly() {
		// given

		// when
		ColumnBounds columnBounds = ColumnBounds.measure("  Col1  Col2  ");

		// then
		then(columnBounds).isEqualTo(ColumnBounds
				.defineBounds(0, 0, 7)
				.and(1, 8, 13));
	}
}