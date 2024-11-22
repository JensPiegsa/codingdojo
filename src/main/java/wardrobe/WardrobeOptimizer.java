package wardrobe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WardrobeOptimizer {
    private final int wallSize;

    public WardrobeOptimizer(int wallSize) {
        this.wallSize = wallSize;
    }

    public List<Wardrobe> getAllCombinations(List<WardrobeElement> possibleElements) {

        if (possibleElements.isEmpty()) {
            return Collections.emptyList();
        }
        List<Wardrobe> allCombinations = new ArrayList<Wardrobe>();
        Wardrobe wardrobe = new Wardrobe();
        int remainingSize = wallSize;
        while (remainingSize >= findSmallest(possibleElements).width()) {
            WardrobeElement wardrobeElement = possibleElements.get(0);
            remainingSize -= wardrobeElement.width();
            wardrobe.addElement(wardrobeElement);
            allCombinations.add(wardrobe);
        }

        return allCombinations;
    }

    public WardrobeElement findSmallest(List<WardrobeElement> possibleElements) {
        WardrobeElement smallest = possibleElements.get(0);
        for (WardrobeElement element : possibleElements) {
            if (element.width() < smallest.width()) {
                smallest = element;
            }
        }
        return smallest;
    }
}
