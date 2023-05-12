package escapethemaze;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.BDDAssertions.then;

@DisplayName("A MazePath")
class MazePathTest {

    @Test @DisplayName("can be reversed.")
    void canBeReversed() {
        final MazePath mazePath = new MazePath()
                .add(Position.of(1, 1))
                .add(Position.of(2, 1));

        final MazePath actual = mazePath.reversed();

        then(actual).isEqualTo(new MazePath()
                .add(Position.of(2, 1))
                .add(Position.of(1, 1)));
    }
    
    @Test @DisplayName("can provide its length.")
    void canProvideItsLength() {
        final MazePath mazePath = new MazePath()
                .add(Position.of(1, 1))
                .add(Position.of(2, 1));

        final int length = mazePath.length();

        then(length).isEqualTo(2);
    }


    @ParameterizedTest
    @DisplayName("can provide its length.")
    @CsvSource(value = {
            "_|0",
            "1,1|1",
            "1,1 2,1|2",
            "1,1 2,1 3,1|3"}, delimiter = '|')
    void canProvideItsLength(final MazePath path, final int expectedLength) {

        final int length = path.length();

        then(length).isEqualTo(expectedLength);
    }
}