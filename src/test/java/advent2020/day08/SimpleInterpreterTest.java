package advent2020.day08;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class SimpleInterpreterTest {
	
	/*
nop +0  | 1         | 0
acc +1  | 2, 8(!)   | 1 ; ! before: 5
jmp +4  | 3         | 1
acc +3  | 6         | 5
jmp -3  | 7         | 5
acc -99 |           |
acc +1  | 4         | 2
jmp -4  | 5         | 2
acc +6  |           |
	 */
	
	@Timeout(value = 500, unit = MILLISECONDS)
	@Test @DisplayName("loop safe execution results in expected value")
	void loopSafeExecutionResultsInExpectedValue() {
		// given
		SimpleInterpreter interpreter = new SimpleInterpreter();
		String code = contentOf(getClass().getResource("example.txt"));
		
		// when
		int actualAccumulatorValue = interpreter.executeLoopSafe(code);
		
		// then
		then(actualAccumulatorValue).isEqualTo(5);
	}

	@Test @DisplayName("simple test: nop")
	void simpleTestNop() {
		SimpleInterpreter interpreter = new SimpleInterpreter();
		String code = "nop +0";

		// when
		int actualAccumulatorValue = interpreter.executeLoopSafe(code);

		// then
		then(actualAccumulatorValue).isEqualTo(0);
	}

	@Test @DisplayName("nop argument is ignored")
	void nopArgumentIsIgnored() {
		SimpleInterpreter interpreter = new SimpleInterpreter();
		String code = "nop +1";

		// when
		int actualAccumulatorValue = interpreter.executeLoopSafe(code);

		// then
		then(actualAccumulatorValue).isEqualTo(0);
	}

	@Test @DisplayName("executes two instructions 2")
	void executesTwoInstructions2() {
		// given
		SimpleInterpreter interpreter = new SimpleInterpreter();
		String code = "nop +1\nacc +1";

		// when
		int actualAccumulatorValue = interpreter.executeLoopSafe(code);

		// then
		then(actualAccumulatorValue).isEqualTo(1);
	}

	@Test @DisplayName("executes two instructions")
	void executesTwoInstructions() {
		// given
		SimpleInterpreter interpreter = new SimpleInterpreter();
		String code = "acc +2";

		// when
		int actualAccumulatorValue = interpreter.executeLoopSafe(code);

		// then
		then(actualAccumulatorValue).isEqualTo(2);
	}

	@Test @DisplayName("executes jump")
	void executesJump() {
		// given
		SimpleInterpreter interpreter = new SimpleInterpreter();
		String code = "jmp +2\nacc -1\nnop +0";

		// when
		int actualAccumulatorValue = interpreter.executeLoopSafe(code);

		// then
		then(actualAccumulatorValue).isEqualTo(0);
	}
}