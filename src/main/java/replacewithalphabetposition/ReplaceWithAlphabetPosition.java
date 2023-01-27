package replacewithalphabetposition;

import static java.util.stream.Collectors.*;

public class ReplaceWithAlphabetPosition {
    
    public static String toNumerical(final String input) {
        return input.toLowerCase().chars()
                .map(i -> i - 'a' + 1)
                .mapToObj(String::valueOf)
                .collect(joining(" "));
    }
}
