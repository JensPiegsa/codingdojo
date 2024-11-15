package wardrobe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.util.Arrays.asList;

class WardrobeOptimizerTest {

    @Test
    void canFindAllCombinations() {

        final int wallSize = 5;
        final WardrobeOptimizer optimizer = new WardrobeOptimizer(wallSize);
        final List<WardrobeElement> possibleElements = new ArrayList<>();
        final List<Wardrobe> combinations = optimizer.getAllCombinations(possibleElements);

        then(combinations).isEmpty();
    }

    @Test @DisplayName("find smallest element")
    void findSmallestElement() {
        final int wallSize = 5;
        final WardrobeOptimizer optimizer = new WardrobeOptimizer(wallSize);

        WardrobeElement wardrobeElementSmall = new WardrobeElement(2, 59);
        WardrobeElement wardrobeElementBig = new WardrobeElement(50, 59);
        final List<WardrobeElement> possibleElements =
                List.of(wardrobeElementSmall, wardrobeElementBig);

        WardrobeElement element = optimizer.findSmallest(possibleElements);
    }

    @Test @DisplayName("cannot find wardrobe element when wall is to short.")
    void cannotFindWardrobeElementWhenWallIsToShort() {

        final int wallSize = 5;
        final WardrobeOptimizer optimizer = new WardrobeOptimizer(wallSize);
        WardrobeElement wardrobeElement = new WardrobeElement(50, 59);
        final List<WardrobeElement> possibleElements =
                List.of(wardrobeElement);
        final List<Wardrobe> combinations = optimizer.getAllCombinations(possibleElements);

        then(combinations).isEmpty();
    }

    @Test @DisplayName("can find all one sized combination.")
    void canFindAllOneSizedResults() {

        final int wallSize = 100;
        final WardrobeOptimizer optimizer = new WardrobeOptimizer(wallSize);
        WardrobeElement wardrobeElement = new WardrobeElement(50, 59);
        final List<WardrobeElement> possibleElements =
                List.of(wardrobeElement);
        final List<Wardrobe> combinations = optimizer.getAllCombinations(possibleElements);

        then(combinations).hasSize(1);
        Wardrobe actualWardrobe = combinations.get(0);
        then(actualWardrobe.getTotalWidth()).isEqualTo(100);
    }
}
