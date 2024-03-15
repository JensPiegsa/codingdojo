package manhattendistance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.BDDAssertions.then;

class ManhattanDistanceTest {

    // https://kata-log.rocks/manhattan-distance-kata

    @ParameterizedTest
    @DisplayName("can calculate distance.")
    @CsvSource({
            "0,   0,   0,   0,   0",
            "0,   1,   0,   0,   1",
            "1,   0,   2,   1,   2",
            "0,   1,   1,   0,   2",
            "0,   -1,   -1,   0,   2"
    })
    void canCalculateDistance(final int x1, final int y1, final int x2, final int y2, final int expectedResult) {

        final Point firstPoint = new Point(x1,y1);
        final Point secondPoint = new Point(x2,y2);
        final Point thirdPoint = Point.at(x2, y2);

        final int result = ManhattanDistance.calculate(firstPoint, secondPoint);

        then(result).isEqualTo(expectedResult);
    }
}