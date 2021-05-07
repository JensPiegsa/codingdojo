package advent2020.day07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

public class Day7Test {


    @Test
    @DisplayName("simple test")
    void simpleTest() {

        // Arrange
        String rules = "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n" +
                "bright white bags contain 1 shiny gold bag.\n" +
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n" +
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" +
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n" +
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
                "faded blue bags contain no other bags.\n" +
                "dotted black bags contain no other bags.";

        // Act
        Day7 day7 = new Day7(rules);
        int result = day7.countColors("shiny gold");

        // Assert
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("complex test")
    void complexTest() {

        // Arrange
        String rules = contentOf(getClass().getResource("input.txt"));

        // Act
        Day7 day7 = new Day7(rules);
        int result = day7.countColors("shiny gold");

        // Assert
        assertThat(result).isEqualTo(185);
    }

}
