package mumbling;

import static java.util.stream.Collectors.*;

import java.util.stream.IntStream;

public class Accumul {
    
    public static String accum(final String s) {
        return IntStream.range(0, s.length())
                .mapToObj(i -> generatePart(s, i))
                .collect(joining("-"));
    }

    private static String generatePart(final String s, final int i) {
        final char c = s.charAt(i);
        final String character = String.valueOf(c);
        final String upperCase = character.toUpperCase();
        final String lowerCase = character.toLowerCase();
        return upperCase + lowerCase.repeat(i);
    }
}