package datamunging;

import java.util.List;
import java.util.Map;

public class DailyWeather {
	
	private final WeatherImporter importer;

	public DailyWeather(final WeatherImporter importer) {
		this.importer = importer;
	}

	public int findMaximumTemperatureSpreadDayNumber(final String weatherDatContent) {
		final Map<String, List<String>> data = importer.importData(weatherDatContent);
		int minimalDifference = Integer.MAX_VALUE;
		int minimalDifferenceDay = 0;
		final List<String> maxTemperatures = data.get("MxT");
		final List<String> minTemperatures = data.get("MnT");
		for (int i = 0; i < data.get("Dy").size(); i++) {
			final String maxTemperature = maxTemperatures.get(i);
			final String minTemperature = minTemperatures.get(i);
			final int currentDifference = Integer.parseInt(maxTemperature) - Integer.parseInt(minTemperature);
			if (currentDifference < minimalDifference) {
				minimalDifference = currentDifference;
				minimalDifferenceDay = Integer.parseInt(data.get("Dy").get(i));
			}
		}
		return minimalDifferenceDay;
	}
}
