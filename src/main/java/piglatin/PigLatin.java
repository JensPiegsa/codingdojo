package piglatin;

public class PigLatin {
    
    public static String pigIt(final String input) {
        
        if (input.isBlank()) {
            return input;
        }

        Character firstLetterOfWord = null;
        String result = "";
        boolean lastCharacterNonLetter = true;

        for (int i = 0; i < input.length(); i++) {
            final char character = input.charAt(i);
            final boolean isLetter = Character.isLetter(character);

            // detection of first letter of word
            if (lastCharacterNonLetter && isLetter) {
                firstLetterOfWord = character;
            }

            // one character after end of word
            if (!isLetter && firstLetterOfWord != null) {
                result += firstLetterOfWord + "ay";
                firstLetterOfWord = null;
            }

            // non letter or inside word (except first letter)
            if (!isLetter || !lastCharacterNonLetter) {
                result += character;
            }
            lastCharacterNonLetter = !isLetter;
        }

        if (firstLetterOfWord != null) {
            result += firstLetterOfWord + "ay";
        }

        return result;
    }
}