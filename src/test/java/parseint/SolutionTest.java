package parseint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SolutionTest {
    
    @Test
    public void fixedTests() {
        Assertions.assertEquals(1, Parser.parseInt("one"));
        Assertions.assertEquals(20, Parser.parseInt("twenty"));
        Assertions.assertEquals(246, Parser.parseInt("two hundred forty-six"));
    }
}