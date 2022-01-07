package aoc2021.day02;

import java.util.List;

public class Dive {

	private int depth;
	private int horizontalPosition;

	public int getPositionProduct() {
		return depth * horizontalPosition;
	}

	public void move(final SubmarineCommand command, final int units) {
		depth = Math.max(0, depth + command.getDepthFactor() * units);
		horizontalPosition += command.getHorizontalDelta() * units;
	}

	public void move(final List<String> commands) {

		for (final String commandLine : commands) {
			final String[] commandAndUnit = commandLine.split(" ");
			final SubmarineCommand command = SubmarineCommand.valueOf(commandAndUnit[0]);
			final int unit = Integer.parseInt(commandAndUnit[1]);
			move(command, unit);
		}
	}
}
