package datamunging;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeatherImporterTest {

	@Test @DisplayName("test single line import.")
	void testSingleLineImport() {
		final String weatherDatContent = contentOf(getClass().getResource("weather-line.dat"));
		final WeatherImporter importer = new WeatherImporter();
		
		final Map<String, List<String>> data = importer.importData(weatherDatContent);
		
		then(data.get("Dy")).containsExactly("1");
		then(data.get("MxT")).containsExactly("88");
		then(data.get("MnT")).containsExactly("59");
		then(data).isEqualTo(Map.of("Dy", List.of("1"), "MxT", List.of("88"), "MnT", List.of("59")));
	}
	
	@Test @DisplayName("test all-lines import.")
	void testAllLinesImport() {
		final String weatherDatContent = contentOf(getClass().getResource("weather.dat"));
		final WeatherImporter importer = new WeatherImporter();
		
		final Map<String, List<String>> data = importer.importData(weatherDatContent);
		
		then(data.get("Dy")).hasSize(30);
		then(data.get("Dy").get(29)).isEqualTo("30");
		then(data.get("MxT").get(29)).isEqualTo("90");
		then(data.get("MnT").get(29)).isEqualTo("45");
	}
}
