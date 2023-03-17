package escapethemaze;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}