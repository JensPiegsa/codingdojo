package advent2020.day03;

import java.util.Arrays;
import java.util.List;

public class TraverseRouteTreeCounter {

	public static final char TREE = '#';
	private final List<String> mapRows;

	public TraverseRouteTreeCounter(final String map) {
		this.mapRows = Arrays.asList(map.split("(\r\n|\n|\r)"));
	}

	public int countTreesCollisions(final int right, final int down) {
		
		int rowLength = mapRows.get(0).length();
		
		int collidingTrees = 0;
		for(int step = 0; step < mapRows.size()/down; step++) {
			final int row = step * down;
			final int col = (step * right) % rowLength;
			if (mapRows.get(row).charAt(col) == TREE) {
				collidingTrees++;
			}
		}
		return collidingTrees;
	}


	public long multiplyTreesOnStandardTraverseRoutes() {
		final long routeOne = countTreesCollisions(1,1);
		final long routeTwo = countTreesCollisions(3,1);
		final long routeThree = countTreesCollisions(5,1);
		final long routeFour = countTreesCollisions(7,1);
		final long routeFive = countTreesCollisions(1,2);

		return routeOne*routeTwo*routeThree*routeFour*routeFive;
	}
}
