package datamunging;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiFunction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Verbesserungsmöglichkeiten
 * // 1. http://apfloat.org/apfloat_java/ | https://github.com/mtommila/apfloat
 * 		// 2. https://dzone.com/articles/arbitrary-precision-numbers | https://github.com/peteroupc/numbers-java
 * 		// 3. https://github.com/bwakell/Huldra
 */
@DisplayName("A table")
class TableTest {

	@Test @DisplayName("returns cell value of requested column and row where function on two columns results in global minimum.")
	void returnsCellValueOfRequestedColumnAndRowWhereFunctionOnTwoColumnsResultsInGlobalMinimum() {
		// given
		final String soccerTableDatContent = contentOf(getClass().getResource("football.dat"));
		final ColumnBounds columnBounds = ColumnBounds
				.defineBounds(0, 0, 6)
				.and(1, 7, 22)
				.and(2,23,28)
				.and(3, 29, 32)
				.and(4,33, 34)
				.and(5,35, 42)
				.and(6, 43, 46)
				.and(7, 47, 49)
				.and(8, 50, 55)
				.and(9, 56, 58);
		final Table soccer = new TableImporter().importData(soccerTableDatContent, columnBounds);
		final BiFunction<Double, Double , Double> f = (a, b) -> Math.abs(a - b);
		
		// when
		final String teamName = soccer.findMinimum(6, 8, f, 1);

		// then
		then(teamName).isEqualTo("Aston_Villa");
	}
}