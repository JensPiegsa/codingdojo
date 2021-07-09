package datamunging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoccerLeagueTableImporter {


	public Map<String, List<Integer>> importData(final String soccerTableDatContent) {

		Map<String, List<Integer>> result = new HashMap<>();

		final String[] lines = soccerTableDatContent.split("\n");
		for (int lineNumber = 1; lineNumber < lines.length; lineNumber++) {
			String line = lines[lineNumber];
			final String[] columns = line.trim().split("[ ]+");

			if (columns.length == 10) {
				String key = columns[1];
				int goalsFor = Integer.parseInt(columns[6]);
				int goalsAgainst = Integer.parseInt(columns[8]);

				result.put(key, List.of(goalsFor, goalsAgainst));
			}
		}
		return result;
	}
}
