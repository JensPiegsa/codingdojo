package allbalancedparentheses;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@DisplayName("A BalancedParentheses generator")
class BalancedParensTest {

    @Test @DisplayName("returns list with empty string for zero.")
    void returnsListWithEmptyStringForZero() {
    	assertThat(BalancedParens.balancedParens(0)).containsExactlyInAnyOrder("");
    }
    
    @Test @DisplayName("returns single pair of parentheses for one.")
    void returnsSinglePairOfParenthesesForOne() {
    	assertThat(BalancedParens.balancedParens(1)).containsExactlyInAnyOrder("()");
    }

    @Test @DisplayName("returns two configurations for two.")
    void returnsTwoConfigurationsForTwo() {
    	assertThat(BalancedParens.balancedParens(2)).containsExactlyInAnyOrder("(())", "()()");
    }

    @Nested @DisplayName("acceptance")
    class Acceptance {

        @Test
        public void testExample() {
            List<String> warriorsList = new ArrayList<String>();
            //test for n = 0
            warriorsList = BalancedParens.balancedParens(0);
            assertEquals(new ArrayList<String>(Arrays.asList(new String[] {""}))
                    ,  warriorsList
            );
            //test for n = 1
            warriorsList = BalancedParens.balancedParens(1);
            assertEquals(new ArrayList<String>(Arrays.asList(new String[] {"()"}))
                    , warriorsList
            );
            //test for n =2
            warriorsList = BalancedParens.balancedParens(2);
            Collections.sort(warriorsList);
            assertEquals(new ArrayList<String>(Arrays.asList(new String[] {"(())","()()"}))
                    , warriorsList
            );
            //test for n = 3
            warriorsList = BalancedParens.balancedParens(3);
            Collections.sort(warriorsList);
            assertEquals(new ArrayList<String>(Arrays.asList(new String[] {"((()))","(()())","(())()","()(())","()()()"}))
                    , warriorsList
            );
            //test for n = 4
            warriorsList = BalancedParens.balancedParens(4);
            Collections.sort(warriorsList);
            assertEquals(new ArrayList<String>(Arrays.asList(new String[] {"(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()","(())(())","(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"}))
                    , warriorsList
            );

        }
    }
}