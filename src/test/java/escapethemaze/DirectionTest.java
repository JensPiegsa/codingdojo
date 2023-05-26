package escapethemaze;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @Test @DisplayName("empty space.")
    void emptySpace() {
        assertThat(Direction.of(' ')).isNull();
    }
    
    @Test @DisplayName("thorn bush")
    void thornBush() {
        assertThat(Direction.of('#')).isNull();
    }
    
    @Test @DisplayName("less than character")
    void lessThanCharacter() {
        assertThat(Direction.of('<')).isEqualTo(Direction.west);
    }
    
    @ParameterizedTest
    @CsvSource({
            "-1,0,west",
            "1,0,east",
            "0,-1,north",
            "0,1,south",
            "-2,0,west",
            "-1,-1,",
            "0,0,"
    })
    void testFromDelta(final int dx, final int dy, final Direction expected) {
        assertThat(Direction.fromDelta(dx, dy)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "north,north,",
            "north,east,TURN_RIGHT",
            "north,south,TURN_BACK",
            "north,west,TURN_LEFT",
            "east,east,",
            "east,south,TURN_RIGHT",
            "east,west,TURN_BACK",
            "east,north,TURN_LEFT",
            "south,south,",
            "south,west,TURN_RIGHT",
            "south,north,TURN_BACK",
            "south,east,TURN_LEFT",
            "west,west,",
            "west,north,TURN_RIGHT",
            "west,east,TURN_BACK",
            "west,south,TURN_LEFT",
    })
    void testTurn(final Direction source, final Direction destination, final NavigationCommand expected) {
        assertThat(source.turnTowards(destination)).isEqualTo(expected);
    }
}