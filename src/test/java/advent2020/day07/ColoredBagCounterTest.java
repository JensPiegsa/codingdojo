package advent2020.day07;

import static org.assertj.core.api.Assertions.contentOf;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColoredBagCounterTest {

	/*
1 light red bags contain 1 bright white bag, 2 muted yellow bags.
2 dark orange bags contain 3 bright white bags, 4 muted yellow bags.
3 bright white bags contain 1 shiny gold bag.
4 muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.

5 - dark olive bags contain 3 faded blue bags, 4 dotted black bags.
6 - vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.

7 - shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
8 - faded blue bags contain no other bags.
9 - dotted black bags contain no other bags.
	 */
	// How many bag colors can eventually contain at least one shiny gold bag? 
	// ###################                                     **********
	
	
	@Test @DisplayName("single rule")
	void singleRule() {
		// given
		final String bagColorRulesPerLine = "light red bags contain 1 bright white bag, 2 muted yellow bags.";
		ColoredBagCounter coloredBagCounter = new ColoredBagCounter(bagColorRulesPerLine);
				
		// when
		BagRule resultRule = coloredBagCounter.parseRule(bagColorRulesPerLine);
		
		// then
		then(resultRule.getColor()).isEqualTo("light red");
		then(resultRule.getContainingBagColors()).containsAnyOf("bright white", "muted yellow");
	}

	@Test @DisplayName("single empty rule")
	void singleEmptyRule() {
		// given
		final String bagColorRulesPerLine = "light red bags contain no other bags.";
		ColoredBagCounter coloredBagCounter = new ColoredBagCounter(bagColorRulesPerLine);

		// when
		BagRule resultRule = coloredBagCounter.parseRule(bagColorRulesPerLine);

		// then
		then(resultRule.getColor()).isEqualTo("light red");
		then(resultRule.getContainingBagColors()).isEmpty();
	}
	
	@Test @DisplayName("all rules")
	void allRules() {
		// given
		String bagRules = contentOf(getClass().getResource("example.txt"));
		ColoredBagCounter coloredBagCounter = new ColoredBagCounter(bagRules);
		
		// when
		List<BagRule> parsedBagRules = coloredBagCounter.readRules(bagRules);
		
		// then
		then(parsedBagRules).hasSize(9);
	}
	
//	@Disabled
	@Test @DisplayName("first test")
	void firstTest() {
		// given
		String bagColorRules = contentOf(getClass().getResource("example.txt"));
		ColoredBagCounter coloredBagCounter = new ColoredBagCounter(bagColorRules);
		
		// when
		int bags = coloredBagCounter.countPossibleBagsFor("shiny gold");
		
		// then
		then(bags).isEqualTo(4);
	}
}