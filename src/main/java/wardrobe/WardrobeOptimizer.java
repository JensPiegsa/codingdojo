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
        int remainingWallSize = wallSize;

        if (remainingWallSize >= findSmallest(possibleElements).width()) {
            List<Wardrobe> combinations = getAllCombinations(possibleElements, wardrobe, remainingWallSize);
            allCombinations.addAll(combinations);
        }
        int max = allCombinations.stream()
                .map(Wardrobe::getTotalWidth)
                .max(Integer::compareTo)
                .orElse(0);
        return allCombinations.stream()
                .filter(w -> w.getTotalWidth() == max)
                .toList();
    }

    private List<Wardrobe> getAllCombinations(List<WardrobeElement> possibleElements, Wardrobe headWardrobe, int remainingWallSize) {
        List<Wardrobe> combinations = new ArrayList<Wardrobe>();

        for (WardrobeElement element : possibleElements) {
            Wardrobe copy = headWardrobe.copy();
            if (element.width() <= remainingWallSize) {
                copy.addElement(element);
                if (copy.getTotalWidth() > 0) {
                    combinations.add(copy);
                }
                List<Wardrobe> tailCombinations = getAllCombinations(possibleElements, copy,
                        remainingWallSize - element.width());
                combinations.addAll(tailCombinations);
            }
        }

        return combinations;
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
