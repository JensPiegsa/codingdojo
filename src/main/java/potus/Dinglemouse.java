package potus;

import java.util.ArrayList;
import java.util.List;

public class Dinglemouse {

	public static boolean allAlone(char[][] house) {
		return new House(house).isAllAlone();
	}

	static Position findPotus(final char[][] house) {
		for (int row = 0; row < house.length; row++) {
			for (int column = 0; column < house[row].length; column++) {
				if (house[row][column] == 'X') {
					return new Position(row, column);
				}
			}
		}
		throw new IllegalArgumentException("Potus not found");
	}

	static class House {

		private char[][] house;

		public House(final char[][] house) {

			this.house = house;
		}

		public boolean isAllAlone() {
			final boolean alone = true;
			final Position potus = findPotus(house);
			List<Position> nextPositions = new ArrayList<>();
			nextPositions.add(potus);
			List<Position> visited = new ArrayList<>();
			while (!nextPositions.isEmpty()) {
				final Position position = nextPositions.get(0);
				nextPositions.remove(position);
				visited.add(position);
				if (isElf(position)) {
					return false;
				}
				final Position right = position.translate(0, 1);
				final Position left = position.translate(0, -1);
				final Position top = position.translate(-1, 0);
				final Position bottom = position.translate(1, 0);
				if (isWall(right) && !visited.contains(right)) {
					nextPositions.add(right);
				}
				if (isWall(left) && !visited.contains(left)) {
					nextPositions.add(left);
				}
				if (isWall(top) && !visited.contains(top)) {
					nextPositions.add(top);
				}
				if (isWall(bottom) && !visited.contains(bottom)) {
					nextPositions.add(bottom);
				}
			}

			return alone;
		}

		private boolean isWall(final Position position) {
			return getCharAt(position) != '#';
		}

		private boolean isElf(final Position position) {
			return getCharAt(position) == 'o';
		}

		private char getCharAt(final Position position) {
			return house[position.row()][position.column()];
		}
	}

	record Position(int row, int column) {

		public Position translate(int deltaRow, int deltaColumn) {
			return new Position(row + deltaRow, column + deltaColumn);
		}
	}
}