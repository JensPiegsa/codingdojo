package word.wrap;

public class Wrapper {
	public static String wrap(final String input, final int length) {

		StringBuilder output = new StringBuilder();
		int firstLetterOfLinePosition = 0;
		int lastSpacePosition = 0;
		for (int index = 0; index < input.length(); index++) {
			final boolean finished = index + 1 == input.length();

			if (input.charAt(index) == ' ' || finished) {
				final boolean wrappingNeeded = index >= length;
				if (wrappingNeeded) {
					if (finished) {
						output.append('\n');
						output.append(input.substring(firstLetterOfLinePosition, index + 1));
					} else {
						output.append(input.substring(firstLetterOfLinePosition, lastSpacePosition));
						output.append('\n');
					}
					firstLetterOfLinePosition = index + 1;
				} else if (finished) {
					output.append(input.substring(firstLetterOfLinePosition, index + 1));
				}

				lastSpacePosition = index;
			}
		}

		return output.toString();
	}
}
