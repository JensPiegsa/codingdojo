package advent2020.day07;

import java.util.Collections;
import java.util.Set;

public class BagRule {
	
	private final String color;
	private final Set<String> containingBagColors;

	public BagRule(final String color, final Set<String> containingBagColors) {
		this.color = color;
		this.containingBagColors = containingBagColors;
	}

	public String getColor() {
		return color;
	}

	public Set<String> getContainingBagColors() {
		return Collections.unmodifiableSet(containingBagColors);
	}

	public boolean containsColor(final String color) {
		return containingBagColors.contains(color);
	}

	@Override
	public String toString() {
		return "BagRule{" +
				"color='" + color + '\'' +
				", containingBagColors=" + containingBagColors +
				'}';
	}
}
