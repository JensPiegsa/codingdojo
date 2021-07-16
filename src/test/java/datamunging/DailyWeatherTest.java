package datamunging;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DailyWeather")
class DailyWeatherTest {
	
	@Test @DisplayName("find day with maximum temperature spread")
	void findDayWithMaximumTemperatureSpread() {
		final String weatherDatContent = contentOf(getClass().getResource("weather.dat"));
		final DailyWeather dailyWeather = new DailyWeather(new WeatherImporter());
		
		final int day = dailyWeather.findMaximumTemperatureSpreadDayNumber(weatherDatContent);
		
		then(day).isEqualTo(14);
	}
}
