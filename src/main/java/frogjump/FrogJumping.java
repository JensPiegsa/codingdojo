package frogjump;

import java.util.HashSet;
import java.util.Set;

public class FrogJumping {

	static final int NO_ESCAPE = -1;

	public static int solution(final int[] input) {

		final Set<Integer> visited = new HashSet<>();
		int index = 0;
		int jumpCount = 0;
		while (isInsideArray(input, index)) {
			
			final int currentElement = input[index];
			if (visited.contains(index) || currentElement == 0) {
				return NO_ESCAPE;
			}

			visited.add(index);
			index += currentElement; 
			jumpCount++;
		}

		return jumpCount;
	}

	private static boolean isInsideArray(final int[] input, final int index) {
		return index >= 0 && index < input.length;
	}
}