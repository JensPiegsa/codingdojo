package christmaslightskata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

@DisplayName("A ChristmasLightGrid")
class ChristmasLightGridTest {

    // https://kata-log.rocks/christmas-lights-kata
    // million lights in a 1000x1000 grid

    @Test
    @DisplayName("starts with all lights off.")
    void startsWithAllLightsOff() {
        ChristmasLightGrid christmasLightGrid = new ChristmasLightGrid();

        for (int x = 0; x <= 999; x++) {
            for (int y = 0; y <= 999; y++) {
                then(christmasLightGrid.isLightOn(x, y)).isFalse();
            }
        }
    }

    @Test
    @DisplayName("can turn light on.")
    void canTurnLightOn() {

        //   0 1 2
        // 0 . . .
        // 1 . . 0
        // 2 . . 0

        // given
        ChristmasLightGrid christmasLightGrid = new ChristmasLightGrid();

        //when
        christmasLightGrid.turnLightOn(1, 2, 2, 2);

        //then
        then(christmasLightGrid.isLightOn(1, 2)).isTrue();
        then(christmasLightGrid.isLightOn(2, 2)).isTrue();
    }

    // FIXME test is kind of weak -> rebuild
    @Test
    @DisplayName("can leave lights out.")
    void canLeaveLightsOut() {

        //   0 1 2
        // 0 . . .
        // 1 . . 0
        // 2 . . 0

        // given
        ChristmasLightGrid christmasLightGrid = new ChristmasLightGrid();

        //when
        christmasLightGrid.turnLightOn(1, 2, 2, 2);

        //then
        then(christmasLightGrid.isLightOn(0, 0)).isFalse();
    }

    // more tests:
    // - can toggle lights
    // - can turn light off
}