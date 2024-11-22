package wardrobe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WardrobeTest {


    // https://kata-log.rocks/configure-wardrobe-kata

    @Test @DisplayName("new class")
    void newClass() {
        Wardrobe wardrobe = new Wardrobe();

        WardrobeElement wardrobeElement50 = new WardrobeElement(50, 59);
        WardrobeElement wardrobeElement75 = new WardrobeElement(75, 62);
        wardrobe.addElement(wardrobeElement50);
        wardrobe.addElement(wardrobeElement75);

        // Assert
        assertThat(wardrobe.getTotalWidth()).isEqualTo(125);
        assertThat(wardrobe.getTotalCost()).isEqualTo(121);
    }
}
