package datamunging;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SoccerLeagueTableImporterTest {

	@Test @DisplayName("can import league table.")
	void canImportLeagueTable() {
		final String soccerTableDatContent = contentOf(getClass().getResource("football.dat"));
		SoccerLeagueTableImporter soccerLeagueTableImporter = new SoccerLeagueTableImporter();

		Map<String, List<Integer>> result = soccerLeagueTableImporter.importData(soccerTableDatContent);

		then(result).isNotNull();
		then(result.get("Arsenal")).containsExactly(79, 36);
	}
}