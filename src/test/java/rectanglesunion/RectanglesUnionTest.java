package rectanglesunion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import rectanglesunion.RectanglesUnion.Point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static rectanglesunion.RectanglesUnion.Rectangle;

@DisplayName("A RectanglesUnion")
public class RectanglesUnionTest {

    @Nested @DisplayName("Unit")
    class Unit {

        @Test @DisplayName("can check inside")
        void canCheckInside() {
        	// given
        	final Rectangle outerRectangle = new Rectangle(0, 4, 11, 7);
        	final Rectangle innerRectangle = new Rectangle(1, 5, 10, 6);

        	// then
        	assertThat(innerRectangle.isInside(outerRectangle)).isTrue();
        	assertThat(outerRectangle.isInside(innerRectangle)).isFalse();
        }        
        
        @Test @DisplayName("can check inside same")
        void canCheckInsideSame() {
        	// given
        	final Rectangle rectangle = new Rectangle(1, 5, 10, 6);

        	// then
        	assertThat(rectangle.isInside(rectangle)).isTrue();
        }

        @Test @DisplayName("can calculate area")
        void canCalculateArea() {
            final Rectangle rectangle = new Rectangle(1, 5, 10, 6);

            assertThat(rectangle.getArea()).isEqualTo(9);

        }

    	@Test @DisplayName("two intersecting rectangles case 2a and 2b")
    	void twoIntersectingRectanglesCase2AAnd2B() {

            final Rectangle outerRectangle = new Rectangle(0, 4, 11, 7);
            final Rectangle innerRectangle = new Rectangle(1, 5, 10, 6);
            assertThat(outerRectangle.getArea()).isEqualTo(33);
            assertThat(innerRectangle.getArea()).isEqualTo(9);
            assertThat(innerRectangle.getUnionArea(outerRectangle)).isEqualTo(33);
            assertThat(outerRectangle.getUnionArea(innerRectangle)).isEqualTo(33);
    	}

        @Test @DisplayName("two rectangles are disjunct")
        void twoRectanglesAreDisjunct() {
            final Rectangle rectangle1 = new Rectangle(0, 4, 11, 7);
            final Rectangle rectangle2 = new Rectangle(21, 15, 30, 16);

            assertThat(rectangle1.isDisjunction(rectangle2)).isTrue();
        }

        @Test @DisplayName("has point inside")
        void hasPointInside() {
            final Rectangle rectangle = new Rectangle(0, 4, 11, 7);

            assertThat(rectangle.isInside(new Point(5, 5))).isTrue();
            assertThat(rectangle.isInside(new Point(15, 5))).isFalse();

        }
        @Test @DisplayName("two intersecting rectangles which overlap in first quadrant")
        void twoIntersectingRectanglesWhichOverlapInFirstQuadrant() {
        	// given

        	// when

        	// then

        }
        
        @Test @DisplayName("returns all four points")
        void returnsAllFourPoints() {
            final Rectangle rectangle = new Rectangle(0, 4, 11, 7);
        	assertThat(rectangle.getPoints()).containsExactlyInAnyOrder(
                    new Point(0, 4),
                    new Point(0, 7),
                    new Point(11, 7),
                    new Point(11, 4));
        }
    }
    
    @Nested @DisplayName("acceptance")
    class Acceptance {

        @Test
        public void testZeroRectangles() {
            final int[][] recs = {};
            assertEquals(0, RectanglesUnion.calculateSpace(recs), "Zero rectangles");
        }

        @Test
        public void testOneRectangle() {
            final int[][] recs = {{0, 4, 11, 6}};
            assertEquals(22, RectanglesUnion.calculateSpace(recs), "One rectangle [0, 4, 11, 6] => 22");
        }
        
    }
}
