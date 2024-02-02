package findtheunknowndigit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RunesTest {

// https://www.codewars.com/kata/546d15cebed2e10334000ed9/train/java

    @ParameterizedTest
    @DisplayName("simple evaluateExpression without '?'")
    @CsvSource({
            "true,       1+1=2",
            "false,      1+1=3",
            "true,       1--1=2",
            "true,       -1--1=0",
            "true,       -2--1=-1"
    })
    void simpleTestWithout(final boolean expected, final String expression) {
        assertEquals(expected, Runes.evaluateExpression(expression));
    }

    @ParameterizedTest 
    @DisplayName("replace question mark with digit.")
    @CsvSource({
            "?+?=?,0,0+0=0",
            "??+?=?2,1,11+1=12"})
    void replaceQuestionMarkWithDigit(final String expressionWithQuestionMark, final int digitValue, final String expected) {
    	assertThat(Runes.replaceQuestionMark(expressionWithQuestionMark,digitValue))
                .isEqualTo(expected);
    }

    @DisplayName("Sample tests")
    @Test
    void testSample() {
        assertEquals(2, Runes.solveExpression("1+1=?"), "expression = \"1+1=?\"");
        assertEquals(6, Runes.solveExpression("123*45?=5?088"), "expression = \"123*45?=5?088\"");
        assertEquals(0, Runes.solveExpression("-5?*-1=5?"), "expression = \"-5?*-1=5?\"");
        assertEquals(-1, Runes.solveExpression("19--45=5?"), "expression = \"19--45=5?\"");
        assertEquals(5, Runes.solveExpression("??*??=302?"), "expression = \"??*??=302?\"");
        assertEquals(-1, Runes.solveExpression("??+??=??"), "expression = \"??+??=??\"");
        // rule: 
        assertEquals(2, Runes.solveExpression("??*1=??"), "expression = \"??*1=??\"");
        assertEquals(2, Runes.solveExpression("?*11=??"), "expression = \"?*11=??\"");
    }
}