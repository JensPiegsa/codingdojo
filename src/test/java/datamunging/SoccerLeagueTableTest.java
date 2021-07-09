package datamunging;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SoccerLeagueTable")
class SoccerLeagueTableTest {

	@Test @DisplayName("find team with smallest difference")
	void findTeamWithSmallestDifference() {
		// given
		final String soccerTableDatContent = contentOf(getClass().getResource("football.dat"));
		SoccerLeagueTable soccerLeagueTable = new SoccerLeagueTable(new SoccerLeagueTableImporter());

		// when
		String teamName = soccerLeagueTable.findTeamWithSmallestDifference(soccerTableDatContent);

		// then
		then(teamName).isEqualTo("Aston_Villa");
	}
}