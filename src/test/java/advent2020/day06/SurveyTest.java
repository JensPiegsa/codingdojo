package advent2020.day06;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SurveyTest {
	
	Survey survey = new Survey();
	
	@Test @DisplayName("new test")
	void newTest() {
		// given
		String answers = contentOf(getClass().getResource("example.txt"));
		
		// when
		long numberOfYes = survey.countYesOfAnyoneInGroups(answers);
		
		// then
		then(numberOfYes).isEqualTo(11L);
	}
		
	@Test @DisplayName("full test")
	void fullTest() {
		// given
		String answers = contentOf(getClass().getResource("input.txt"));

		// when
		long numberOfYes = survey.countYesOfAnyoneInGroups(answers);

		// then
		then(numberOfYes).isEqualTo(6735L);
	}
	
	@Test @DisplayName("part two small test")
	void partTwoSmallTest() {
		// given
		String answers = contentOf(getClass().getResource("example.txt"));

		// when
		long numberOfYes = survey.countYesOfEveryoneInGroups(answers);

		// then
		then(numberOfYes).isEqualTo(6L);
	}
	
	@Test @DisplayName("part two full test")
	void partTwoFullTest() {
		// given
		String answers = contentOf(getClass().getResource("input.txt"));

		// when
		long numberOfYes = survey.countYesOfEveryoneInGroups(answers);

		// then
		then(numberOfYes).isEqualTo(3221L);
	}
}