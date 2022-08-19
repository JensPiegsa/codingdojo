package arewealternate;

import static java.util.stream.Collectors.*;

import java.util.Set;
import java.util.stream.IntStream;

@SuppressWarnings("StaticCollection")
public class Solution {

    private static final Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
    private static final Set<Character> consonants = IntStream.rangeClosed((int) 'a', (int) 'z')
            .filter(c -> !vowels.contains((char) c)).mapToObj(c -> (Character) (char) c )
            .collect(toSet());

    public static boolean isAlt(final String word) {
        final char first = word.charAt(0);
        final char second = word.charAt(1);
        return 
                (vowels.contains(first) && consonants.contains(second))
                || (consonants.contains(first) && vowels.contains(second));
    }

    private Solution() {
    }
}