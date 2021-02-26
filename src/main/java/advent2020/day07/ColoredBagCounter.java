package advent2020.day07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ColoredBagCounter {

	private final List<BagRule> rules;

	public ColoredBagCounter(final String bagColorRulesPerLine) {
		rules = readRules(bagColorRulesPerLine);
	}


	public int countPossibleBagsFor(final String color) {
		return countRecursively(color, null);
	}

	private int countRecursively(final String color, final String secondColor) {
		int result = 0;

		for (BagRule rule : rules) {
			
			if (rule.containsColor(color)) {
				result++;
			} else if (secondColor != null) {
				if (rule.containsColor(secondColor)) {
					for (String containingBagColor : rule.getContainingBagColors()) { // TODO rule from secondColor
						result += countRecursively(color, containingBagColor);
					}
				}
			} else {
				for (String containingBagColor : rule.getContainingBagColors()) {
					result += countRecursively(color, containingBagColor);
				}
			}
		}
		
		
//		boolean found = false;
//		for (BagRule rule : rules) {
//			if (rule.containsColor(color)) {
//				found = true;
//				if (firstRun) {
//					result++;
//				}
//				final int rr = countRecursively(rule.getColor(), false);
//				System.out.println("rr: " + rr + " rule: " + rule);
//				result += rr;
//			}
//		}
//		if (!firstRun && !found) {
//			result++;
//		}
		return result;
	}

	List<BagRule> readRules(final String bagRules) {
		final String[] lines = bagRules.split("(\r\n|\n|\r)");

		List<BagRule> allRules = new ArrayList<>();
		
		for (String line : lines) {
			allRules.add(parseRule(line));
		}
		
		return allRules;
	}

	BagRule parseRule(final String bagColorRule) {
		final String[] split = bagColorRule.split(" contain ");
		final String leftSide = split[0];
		final String rightSide = split[1];
		
		String color = leftSide.substring(0, leftSide.length() - 5);
		final String[] content = rightSide.split(", ");

		Set<String> containedColors = new HashSet<>();
		if (!rightSide.startsWith("no ")) {
			for (String entry : content) {

				final int firstIndex = entry.indexOf(" ");
				final int lastIndex = entry.lastIndexOf(' ');
				final String oneContainingColor = entry.substring(firstIndex + 1, lastIndex);
				containedColors.add(oneContainingColor.trim());
			}
		}
		return new BagRule(color, containedColors);
	}
}
