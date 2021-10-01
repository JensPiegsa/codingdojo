package datamunging;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BiFunction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A table processor")
class TableProcessorTest {

	@Test @DisplayName("test")
	void test() {
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
		final BiFunction<String, String , Number> f = (a, b) -> Integer.parseInt(a) - Integer.parseInt(b);
		
		// when
		final String teamName = soccer.findMinimum(6, 8, f, 1);

		// then
		then(teamName).isEqualTo("Aston_Villa");
	}
}