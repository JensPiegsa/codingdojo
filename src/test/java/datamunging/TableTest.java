package datamunging;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

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
@SuppressWarnings("NewMethodNamingConvention")
class TableTest {

	@Test @DisplayName("returns cell value of requested column and row where function on two columns results in global minimum (1).")
	void returnsCellValueOfRequestedColumnAndRowWhereFunctionOnTwoColumnsResultsInGlobalMinimum1() {
		final Table soccer = readSoccerTable();
		final BiFunction<Double, Double , Double> f = (a, b) -> Math.abs(a - b);
		
		final String teamName = soccer.findMinimum(6, 8, f, 1);

		then(teamName).isEqualTo("Aston_Villa");
	}

	@Test @DisplayName("returns cell value of requested column and row where function on two columns results in global minimum (2).")
	void returnsCellValueOfRequestedColumnAndRowWhereFunctionOnTwoColumnsResultsInGlobalMinimum2() {
		final Table weather = readWeatherTable();
		final BiFunction<Double, Double , Double> f = (a, b) -> Math.abs(a - b);

		final String dayNumber = weather.findMinimum(1, 2, f, 0);

		then(dayNumber).isEqualTo("14");
	}

	@Test @DisplayName("returns cell value of requested column and row where function on two columns results in global maximum.")
	void returnsCellValueOfRequestedColumnAndRowWhereFunctionOnTwoColumnsResultsInGlobalMaximum() {
		final Table weather = readWeatherTable();
		final BiFunction<Double, Double , Double> f = (a, b) -> Math.abs(a - b);

		final String dayNumber = weather.findMaximum(1, 2, f, 0);

		then(dayNumber).isEqualTo("9");
	}

	@Test @DisplayName("returns number of data rows.")
	void returnsNumberOfDataRows() {
		final Table weather = readWeatherTable();
		final int rowCount = weather.getRowCount();
		then(rowCount).isEqualTo(32);
	}

	@Test @DisplayName("return expected cell value.")
	void returnExpectedCellValue() {
		final Table weather = readWeatherTable();
		final String actual = weather.getCellValue(1,3);
		then(actual).isEqualTo("74");
	}

	private static Table readSoccerTable() {
		final String soccerTableDatContent = contentOf(TableTest.class.getResource("football.dat"));
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
		return soccer;
	}

	private static Table readWeatherTable() {
		final String weatherTableDatContent = contentOf(TableTest.class.getResource("weather.dat"));
		final ColumnBounds columnBounds = ColumnBounds
				.defineBounds(15, 80, 82)
				.and(16, 83, 88);
		return new TableImporter().importData(weatherTableDatContent, columnBounds);
	}
}