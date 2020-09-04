package multipliesof3or5;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SolutionTest {
    @Test
    public void test() {
      assertEquals(23, new Solution().solution(10));
    }
    
    @Test
    public void negativeTest() {
      assertEquals(0, new Solution().solution(-10));
    }

    @Test
    public void bigNumberTest() {
        // warning max_int
        assertEquals(1404932684, new Solution().solution(1000000));
    }
    
}