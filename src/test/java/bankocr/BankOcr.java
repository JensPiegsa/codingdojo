package bankocr;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BankOcr {
	public List<String> parse(final Path path) {
		try (final BufferedReader bufferedReader = Files.newBufferedReader(path)){
			final String firstLine = bufferedReader.readLine();
			final String secondLine = bufferedReader.readLine();
			final String thirdLine = bufferedReader.readLine();

		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
		return null;
	}
}
