package uniqueinorder;

import static org.assertj.core.api.BDDAssertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UniqueInOrderTest {

    @Test
    @DisplayName("returns letters unique in order.")
    void returnsLettersUniqueInOrder() {
        final char[] unique = UniqueInOrder.makeUnique("AAAAbbbCCA");
        then(unique).containsExactly('A', 'b', 'C', 'A');
    }

    @Test @DisplayName("bug with Z.")
    void bugWithZ() {
        final char[] unique = UniqueInOrder.makeUnique("Z");
        then(unique).containsExactly('Z');
    }
}
