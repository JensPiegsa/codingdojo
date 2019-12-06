package bowling;

import static java.lang.Integer.parseInt;

public class Bowling {

	private static final String STRIKE = "X";
	private static final String SPARE = "/";
	private static final String ZERO = "-";

	private String frames;

	public Bowling(final String frames) {
		this.frames = frames;
	}

	public int getTotalScore() {

		String[] strings = frames
				.split("");

		int maxLength = 20;
		int result = 0;

		for (int i = 0; i < maxLength; i++){

			switch (strings[i]) {
				case STRIKE:
					maxLength--;
					result += 10 + parse(strings[i + 1]) + parse(strings[i+2]);
					break;
				case SPARE:
					result += 10 - parse(strings[i-1]) + parse(strings[i+1]);
					break;
				default:
					result += parse(strings[i]);
					break;
			}
		}

		return result;
	}

	private int parse(final String character) {
		if (character.equals(STRIKE)) {
			return  10;
		}

		if (character.equals((ZERO))) {
			return 0;
		}

		return parseInt(character);
	}
}
