package datamunging;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WeatherImporter {
	
	public Map<String, List<String>> importData(final String weatherDatContent) {
		final String[] lines = weatherDatContent.split("\n");
		final String[] headers = readLine(lines[0]);
		final LinkedHashMap<String, List<String>> data = new LinkedHashMap<>();
		
		data.put(headers[0], new ArrayList<>());
		data.put(headers[1], new ArrayList<>());
		data.put(headers[2], new ArrayList<>());

		for (int i = 2; i < lines.length - 1; i++) {
			final String line = lines[i];
			final String[] splitLine = readLine(line);
			data.get(headers[0]).add(splitLine[0]);
			data.get(headers[1]).add(splitLine[1]);
			data.get(headers[2]).add(splitLine[2]);
		}
		return data;
	}

	@SuppressWarnings("DynamicRegexReplaceableByCompiledPattern")
	private static String[] readLine(final String line) {
		return line.trim().split("[ *]+");
	}
}