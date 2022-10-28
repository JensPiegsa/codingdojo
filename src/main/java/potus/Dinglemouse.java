package potus;

import java.util.ArrayList;
import java.util.List;

public class Dinglemouse {

    public static boolean allAlone(char[][] houseMap) {
        return new House(houseMap).isAllAlone();
    }

    static class House {

        private final char[][] houseMap;

        public House(final char[][] houseMap) {
            this.houseMap = houseMap;
        }

        public boolean isAllAlone() {
            final Position potus = findPotus();
            final List<Position> visited = new ArrayList<>();
            final List<Position> nextVisits = new ArrayList<>();
            nextVisits.add(potus);

            while (!nextVisits.isEmpty()) {
                final Position position = nextVisits.get(0);
                if (isElf(position)) {
                    return false;
                }
                nextVisits.remove(position);
                visited.add(position);

                for (Position neighbour : position.neighbours()) {
                    if (!nextVisits.contains(neighbour) && !visited.contains(neighbour) && !isWall(neighbour)) {
                        nextVisits.add(neighbour);
                    }
                }
            }

            return true;
        }

	    Position findPotus() {
		    for (int row = 0; row < houseMap.length; row++) {
			    for (int column = 0; column < houseMap[row].length; column++) {
				    if (houseMap[row][column] == 'X') {
					    return new Position(row, column);
				    }
			    }
		    }
		    throw new IllegalArgumentException("Potus not found");
	    }

        private boolean isWall(final Position position) {
            return getCharAt(position) == '#';
        }

        private boolean isElf(final Position position) {
            return getCharAt(position) == 'o';
        }

        private char getCharAt(final Position position) {
            return houseMap[position.row()][position.column()];
        }

	    @Override
	    public String toString() {
		    final StringBuilder builder = new StringBuilder();
		    for (final char[] chars : houseMap) {
			    for (final char aChar : chars) {
				    builder.append(aChar);
			    }
			    builder.append('\n');
		    }
		    return builder.toString();
	    }
    }

    record Position(int row, int column) {

        public Position translate(int deltaRow, int deltaColumn) {
            return new Position(row + deltaRow, column + deltaColumn);
        }

        public List<Position> neighbours() {
            return List.of(
                    translate(0, 1),
                    translate(0, -1),
                    translate(-1, 0),
                    translate(1, 0));
        }
    }
}