package datamunging;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		@Test @DisplayName("with weather example columns.")
		void withForWeatherExampleColumns() {
			final String header = "  Dy MxT   MnT   AvT   HDDay  AvDP 1HrP TPcpn WxType PDir AvSp Dir MxS SkyC MxR MnR AvSLP";
			final ColumnBounds customColumnBounds = ColumnBounds
					.defineBounds(15, 80, 82)
					.and(16, 83, 88);
			final ColumnBounds columnBounds = ColumnBounds.measure(header).merge(customColumnBounds);
			final String lineToBeCut = "  17  81    57    69          51.7       0.00 T       260  9.1 270  29* 5.2  90 34 1012.5";

			final String[] actual = columnBounds.cut(lineToBeCut);
			
			System.out.println(columnBounds);
			System.out.println(header);
			System.out.println(lineToBeCut);
		}

//		@Test @DisplayName("football header.")
//		void footballHeader() {
//			// given
//			final ColumnBounds columnBounds = ColumnBounds.measure("X      Team            P     W    L   D    F   X  A     Pts");
//	
//			System.out.println(columnBounds);
//		}
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
		
		@Test @DisplayName("working for many columns.")
		void workingForManyColumns() {
			final String header = "0 1 2 3 4 5 6 7 8 9 0 1 ";
			final ColumnBounds columnBounds = ColumnBounds.measure(header);
			final String string = columnBounds.toString();
			then(string).isEqualTo("ColumnBounds[leftColumnBounds={0=0, 1=2, 2=4, 3=6, 4=8, 5=10, 6=12, 7=14, 8=16, 9=18, 10=20, 11=22}, rightColumnBounds={0=1, 1=3, 2=5, 3=7, 4=9, 5=11, 6=13, 7=15, 8=17, 9=19, 10=21, 11=23}]:\n" +
					"||||||||||||||||||||||||\n" +
					"001122334455667788990011");
		}

		@Test @DisplayName("working for weather example columns.")
		void workingForWeatherExampleColumns() {
			final String header = "  Dy MxT   MnT   AvT   HDDay  AvDP 1HrP TPcpn WxType PDir AvSp Dir MxS SkyC MxR MnR AvSLP";

			final ColumnBounds customColumnBounds = ColumnBounds
					.defineBounds(15, 80, 82)
					.and(16, 83, 88);
			final ColumnBounds columnBounds = ColumnBounds.measure(header).merge(customColumnBounds);
			final String string = columnBounds.toString();

			System.out.println(string);
			System.out.println(header);
			System.out.println("  17  81    57    69          51.7       0.00 T       260  9.1 270  29* 5.2  90 34 1012.5");

			then(string).isEqualTo("ColumnBounds[leftColumnBounds={0=0, 1=5, 2=11, 3=17, 4=23, 5=30, 6=35, 7=40, 8=46, 9=53, 10=58, 11=63, 12=67, 13=71, 14=76, 15=80, 16=83}, rightColumnBounds={0=4, 1=10, 2=16, 3=22, 4=29, 5=34, 6=39, 7=45, 8=52, 9=57, 10=62, 11=66, 12=70, 13=75, 14=79, 15=82, 16=88}]:\n" +
					"|---||----||----||----||-----||---||---||----||-----||---||---||--||--||---||--||-||----|\n" +
					"00000111111222222333333444444455555666667777778888888999990000011112222333334444555666666");
		}
	}

	@Test @DisplayName("can equal.")
	void canEqual() {
		// given
		ColumnBounds firstBounds = ColumnBounds
				.defineBounds(0, 0, 2)
				.and(1, 3,5);
		ColumnBounds secondBounds = ColumnBounds
				.defineBounds(0, 0, 2);
		ColumnBounds thirdBounds = ColumnBounds
				.defineBounds(0, 0, 2)
				.and(1, 3,5);

		Set<ColumnBounds> bounds = new HashSet<>();
		bounds.add(firstBounds);
		bounds.add(secondBounds);

		secondBounds.and(1, 3, 5);
		bounds.add(thirdBounds);
		// when

		// then
		then(bounds).hasSize(2);
		then(bounds).containsExactlyInAnyOrder(firstBounds, secondBounds);
	}
}