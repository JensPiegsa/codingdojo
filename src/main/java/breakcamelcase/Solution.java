package breakcamelcase;

public class Solution {

    public static String camelCase(final String input) {

        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append(' ');
            }
            result.append(c);
        }
        return result.toString();
//      return input.codePoints()
//              .mapToObj(i -> (char) i)
//              .map(c -> Character.isUpperCase(c) ? " " + c : String.valueOf(c))
//              .collect(joining());
    }
}