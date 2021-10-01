package datamunging;

import static org.assertj.core.api.Assertions.contentOf;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A table processor")
class TableProcessorTest {

	@Test @DisplayName("test")
	void test() {
		// given
		final String soccerTableDatContent = contentOf(getClass().getResource("football.dat"));
		Table soccer = new TableImporter().importData(soccerTableDatContent);
		// when

		// then

	}
}