package advent2020.day07;

import java.util.HashSet;
import java.util.Set;

public class Day7 {

    private final String[] rules;

    public Day7(String rules) {
        this.rules = rules.split("\r\n|\n");
    }

    public int countColors(String givenColor) {
        Set<String> colors = getColors(givenColor, new HashSet<>());
        return colors.size();
    }

    private Set<String> getColors(String givenColor, HashSet<String> colors) {

        HashSet<String> newColors = new HashSet<>();

        for (String rule : rules) {
            final String[] parts = rule.split(" contain ");
            String leftHand = parts[0];
            String rightHand = parts[1];

            if (rightHand.contains(givenColor)) {
                String color = leftHand.substring(0, leftHand.indexOf(' ', 1 + leftHand.indexOf(' ')));
                newColors.add(color);
            }
        }

        for (String color : newColors) {
            colors.addAll(getColors(color, colors));
        }

        colors.addAll(newColors);
        return colors;
    }
}
