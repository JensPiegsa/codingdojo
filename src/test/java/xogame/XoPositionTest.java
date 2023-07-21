package xogame;

import static org.assertj.core.api.BDDAssertions.*;
import static xogame.XoPosition.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("An XoPosition")
class XoPositionTest {

    XoPosition xoPosition;
    
    @Test @DisplayName("has all da positions, man!")
    void hasAllDaPositionsMan() {
    
        assertThat(XoPosition.values()).containsExactly(
                upperLeft, upperMiddle, upperRight,
                centerLeft, centerMiddle, centerRight,
                lowerLeft, lowerMiddle, lowerRight
        );
    }


}