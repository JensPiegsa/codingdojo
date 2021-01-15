package advent2020.day02;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PasswordValidatorTest {

	PasswordValidator passwordValidator = new PasswordValidator();
	
	@Test @DisplayName("test example")
	void testExample() {
		// given
		List<String> passwords = asList("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc");

		// when
		long numberOfValidPasswords = passwordValidator.countValid(passwords);
		
		// then
		then(numberOfValidPasswords).isEqualTo(2);
	}

	@Test @DisplayName("full test")
	void fullTest() throws IOException, URISyntaxException {
		// given
		List<String> passwords = readLinesAsStringFromFile("input.txt");
		
		// when
		long numberOfValidPasswords = passwordValidator.countValid(passwords);

		// then
		then(numberOfValidPasswords).isEqualTo(572);
	}
	
	private List<String> readLinesAsStringFromFile(final String fileName) throws URISyntaxException, IOException {
		final URI uri = PasswordValidatorTest.class.getResource(fileName).toURI();
		return Files.readAllLines(Paths.get(uri))
				.stream()
				.filter(line -> !line.isEmpty())
				.collect(toList());
	}
}