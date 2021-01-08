package advent2020.day01;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExpenseReportTest {

	ExpenseReport expenseReport = new ExpenseReport();
	
	@Test @DisplayName("returns expected multiply product")
	void returnsExpectedMultiplyProduct() {
		// given
		List<Integer> expenses = asList(1721, 979, 366, 299, 675, 1456);

		// when
		final int product = expenseReport.calculateProduct(2020, expenses);

		// then
		then(product).isEqualTo(514579);
	}

	@Test @DisplayName("returns expected multiply product from file")
	void returnsExpectedMultiplyProductFromFile() throws URISyntaxException, IOException {
		// given
		List<Integer> expenses = readLinesAsNumbersFromFile("input.txt");

		// when
		final int product = expenseReport.calculateProduct(2020, expenses);

		// then
		then(product).isEqualTo(482811);
	}

	private List<Integer> readLinesAsNumbersFromFile(final String fileName) throws URISyntaxException, IOException {
		final URI uri = ExpenseReportTest.class.getResource(fileName).toURI();
		return Files.readAllLines(Paths.get(uri))
				.stream()
				.filter(line -> !line.isEmpty())
				.map(Integer::parseInt)
				.collect(toList());
	}

	@Test @DisplayName("returns expected multiply product with three numbers")
	void returnsExpectedMultiplyProductWithThreeNumbers() {
		// given
		List<Integer> expenses = asList(1721, 979, 366, 299, 675, 1456);
		
		// when
		final int product = expenseReport.calculateProductOfThree(2020, expenses);
		
		// then
		then(product).isEqualTo(241861950);
	}

	@Test @DisplayName("returns expected multiply product from file with three numbers")
	void returnsExpectedMultiplyProductFromFileWithThreeNumbers() throws URISyntaxException, IOException {
		// given
		List<Integer> expenses = readLinesAsNumbersFromFile("input.txt");

		// when
		final int product = expenseReport.calculateProductOfThree(2020, expenses);

		// then
		then(product).isEqualTo(193171814);
	}
}
