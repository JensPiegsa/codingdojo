package rectanglesunion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("A RectanglesUnion")

public class RectanglesUnionTest {


    
    @Nested @DisplayName("acceptance")
    class Acceptance {

        @Test
        public void testZeroRectangles() {
            int[][] recs = {};
            assertEquals(0, RectanglesUnion.calculateSpace(recs), "Zero rectangles");
        }

        @Test
        public void testOneRectangle() {
            int[][] recs = {{0, 4, 11, 6}};
            assertEquals(22, RectanglesUnion.calculateSpace(recs), "One rectangle [0, 4, 11, 6] => 22");
        }
    }
}
