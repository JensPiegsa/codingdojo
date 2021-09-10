package datamunging;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ColumnBounds")
class ColumnBoundsTest {
	
	@Nested @DisplayName("can measure")
	class CanMeasure {

		@Test @DisplayName("first column with leading spaces.")
		void firstColumnWithLeadingSpaces() {
			// given
			final String headerLine = "  Col1  Col2  ";

			// when
			final ColumnBounds columnBounds = ColumnBounds.measure(headerLine);

			// then
			then(columnBounds).isEqualTo(ColumnBounds
					.defineBounds(0, 0, 7)
					.and(1, 8, 13));
		}

		@Test @DisplayName("and merge (1).")
		void andMerge1() {
			// given
			final String header = "MxR  MnR AvSLP BOB"; // measured | custom | custom | measured
			//                     |---||-||-----||-|
			//                     000001112222222333

			final ColumnBounds customColumnBounds = ColumnBounds
					.defineBounds(1, 5, 7)
					.and(2, 8, 14);

			// when
			final ColumnBounds columnBounds = ColumnBounds.measure(header).merge(customColumnBounds);

			// then
			then(columnBounds).isEqualTo(
					ColumnBounds.defineBounds(0, 0, 4)
							.and(1, 5, 7)
							.and(2, 8, 14)
							.and(3,15,17));
		}

		@Test @DisplayName("and merge (2).")
		void andMerge2() {
			// given
			final String header = "MxR MnR AvSLP"; // measured | custom | custom
			//                    "|--||-||----|"
			//                     0000111222222

			final ColumnBounds customColumnBounds = ColumnBounds
					.defineBounds(1, 4, 6)
					.and(2, 7, 12);

			// when
			final ColumnBounds columnBounds = ColumnBounds.measure(header).merge(customColumnBounds);

			// then
			then(columnBounds).isEqualTo(
					ColumnBounds.defineBounds(0, 0, 3)
							.and(1, 4, 6)
							.and(2, 7, 12));
		}
	}
	
	@Nested @DisplayName("can cut")
	class CanCut {

		@Test @DisplayName("with measured bounds.")
		void withMeasuredBounds() {
			// given
			final ColumnBounds columnBounds = ColumnBounds.measure("   AAA BB ");
	
			// when
			final String[] individualColumn = columnBounds.cut("   aaa bb ");
	
			// then
			then(individualColumn).containsExactly("   aaa ", "bb ");
		}

//		@Test @DisplayName("football header.")
//		void footballHeader() {
//			// given
//			final ColumnBounds columnBounds = ColumnBounds.measure("X      Team            P     W    L   D    F   X  A     Pts");
//	
//			System.out.println(columnBounds);
//		}
	
		@Test @DisplayName("with empty header.")
		void withEmptyHeader() {
			// given
			final String header = "     MnR       BOB"; // custom | custom | custom | custom
			//                     |---||-||-----||-|
			//                     000001112222222333
	
			final ColumnBounds customColumnBounds = ColumnBounds.defineBounds(0, 0, 4)
					.and(1, 5, 7)
					.and(2, 8, 14)
					.and(3,15,17);
	
			// when
			final String[] result = customColumnBounds.cut(header);
	
			// then
			then(result).containsExactly("     ","MnR","       ","BOB");
		}


		@Test @DisplayName("with empty header (2).")
		void withEmptyHeader2() {
			// given
			final String header = "     MnR       BOB"; // custom | custom | custom | custom
			//                     |---||-||----||--|
			//                     000001112222223333

			final ColumnBounds customColumnBounds = ColumnBounds.defineBounds(0, 0, 4)
					.and(1, 5, 7)
					.and(2, 8, 13)
					.and(3,14,17);

			// when
			final String[] result = customColumnBounds.cut(header);

			// then
			then(result).containsExactly("     ","MnR","      "," BOB");
		}
	}

	@Nested @DisplayName("has toString()")
	class HasToString {
		
		@Test @DisplayName("working for empty bounds.")
		void workingForEmptyBounds() {
			final ColumnBounds columnBounds = new ColumnBounds();
			final String string = columnBounds.toString();
			then(string).isEqualTo("ColumnBounds[empty]");
		}

		@Test @DisplayName("working for single char column.")
		void workingForSingleCharColumn() {
			final String column = "c";
			final ColumnBounds columnBounds = ColumnBounds.measure(column);
			final String string = columnBounds.toString();
			then(string).isEqualTo("ColumnBounds[leftColumnBounds={0=0}, rightColumnBounds={0=0}]:\n" +
					"|\n" +
					"0");
		}

		@Test @DisplayName("working for single column.")
		void workingForSingleColumn() {
			final String column = " col ";
			final ColumnBounds columnBounds = ColumnBounds.measure(column);
			final String string = columnBounds.toString();
			then(string).isEqualTo("ColumnBounds[leftColumnBounds={0=0}, rightColumnBounds={0=4}]:\n" +
					"|---|\n" +
					"00000");
		}
		
		@Test @DisplayName("working for many column.")
		void workingForManyColumn() {
			final String column = "0 1 2 3 4 5 6 7 8 9 0 1 ";
			final ColumnBounds columnBounds = ColumnBounds.measure(column);
			final String string = columnBounds.toString();
			then(string).isEqualTo("ColumnBounds[leftColumnBounds={0=0, 1=2, 2=4, 3=6, 4=8, 5=10, 6=12, 7=14, 8=16, 9=18, 10=20, 11=22}, rightColumnBounds={0=1, 1=3, 2=5, 3=7, 4=9, 5=11, 6=13, 7=15, 8=17, 9=19, 10=21, 11=23}]:\n" +
					"||||||||||||||||||||||||\n" +
					"001122334455667788990011");
		}
	}
}