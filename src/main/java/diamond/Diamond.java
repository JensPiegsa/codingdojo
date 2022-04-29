package diamond;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Diamond {

	public static List<String> create(final char letter) {
		final List<String> diamondLines = new ArrayList<>();
		final int halfHeight = letter - 'A';
		for (int i = 0; i <= halfHeight; i++) {
			diamondLines.add("" + (char) ('A' + i));
		}
		return diamondLines;
	}
}
