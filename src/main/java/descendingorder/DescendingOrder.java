package descendingorder;

import static java.util.Collections.*;
import static java.util.stream.Collectors.*;

public class DescendingOrder {
	
	public static int sortDesc(final int number) {

		final String string = String.valueOf(number);
		final String descendingString = string.chars()
				.mapToObj(i -> (char) i)
				.sorted(reverseOrder())
				.map(String::valueOf)
				.collect(joining());
		return Integer.parseInt(descendingString);
	}
}