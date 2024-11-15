package wardrobe;

import java.util.ArrayList;
import java.util.List;

public class Wardrobe {

    List<WardrobeElement> allElements = new ArrayList<>();

    public void addElement(WardrobeElement element) {
        allElements.add(element);
    }


    public int getTotalWidth() {
        return allElements.stream()
                .mapToInt(WardrobeElement::width)
                .sum();
    }

    public int getTotalCost() {
        return allElements.stream()
                .mapToInt(WardrobeElement::cost)
                .sum();
    }
}
