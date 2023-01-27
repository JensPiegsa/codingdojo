package uniqueinorder;


public class UniqueInOrder {

    public static char[] makeUnique(final String input) {
        char lastChar = 'Z';
        var result = "";
        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (i == 0 || c != lastChar) {
                result += c;
                lastChar = c;
            }
        }
        return result.toCharArray();
    }
}
