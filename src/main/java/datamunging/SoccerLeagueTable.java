package datamunging;

import java.util.List;
import java.util.Map;

public class SoccerLeagueTable {

	private final SoccerLeagueTableImporter importer;

	public SoccerLeagueTable(final SoccerLeagueTableImporter importer) {
		this.importer = importer;
	}

	public String findTeamWithSmallestDifference(final String soccerTableDatContent) {
		final Map<String, List<Integer>> teams = importer.importData(soccerTableDatContent);

		String teamNameWithSmallestDifference = null;
		int smallestDifference = Integer.MAX_VALUE;

		for (Map.Entry<String, List<Integer>> team : teams.entrySet()) {
			final int currentDifference = Math.abs(team.getValue().get(0) - team.getValue().get(1));
			if (currentDifference < smallestDifference) {
				smallestDifference = currentDifference;
				teamNameWithSmallestDifference = team.getKey();
			}
		}
		return teamNameWithSmallestDifference;
	}
}
