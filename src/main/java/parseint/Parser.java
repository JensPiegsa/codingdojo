package parseint;

import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {

    private static final Map<String, Integer> lookup = new LinkedHashMap<>();
    
    static {
        final Map<String, Integer> singleDigits = Map.of(
                "zero", 0,
                "one", 1,
                "two", 2,
                "three", 3,
                "four", 4,
                "five", 5,
                "six", 6,
                "seven", 7,
                "eight", 8,
                "nine", 9);

        final Map<String, Integer> teenDigits = Map.of(
                "eleven", 11,
                "twelve", 12,
                "thirteen", 13,
                "fourteen", 14,
                "fifteen", 15,
                "sixteen", 16,
                "seventeen", 17,
                "eighteen", 18,
                "nineteen", 19);
        final Map<String, Integer> twoDigitNumbers = Map.of(
                "ten", 10,
                "twenty", 20,
                "thirty",30,
                "forty",40,
                "fifty",50,
                "sixty",60,
                "seventy",70,
                "eighty",80,
                "ninety",90
        );
        final Map<String, Integer> threeDigitNumbers = Map.of(
                "hundred", 100,
                "thousand", 1_000,
                "million", 1_000_000
        );
        
        lookup.putAll(singleDigits);
        lookup.putAll(teenDigits);
        lookup.putAll(twoDigitNumbers);
        lookup.putAll(threeDigitNumbers);
    }

    public static int parseInt(final String numberInWords) {
        
        final String cleanNumbers = numberInWords.replaceAll(" and ", " ");


        final Integer integer = lookup.get(cleanNumbers);
        
        if (integer == null) {
            final String[] words = cleanNumbers.split(" ", 3);
            
            if (words.length >= 2) {
                final Integer largeNumberCount = words[0].contains("-") ? parseDashedNumberWord(words[0]) : lookup.get(words[0]);
                final Integer largeNumbers = lookup.get(words[1]);
                int result = largeNumberCount * largeNumbers;
                if (largeNumbers != 1000 && cleanNumbers.contains("thousand")) {
                    result *= 1000;
                }
                if (words.length > 2) {
                    result += parseInt(words[2]);
                }
                
                return result;
            }

            return parseDashedNumberWord(cleanNumbers);
        }
        return integer;
    }

    private static int parseDashedNumberWord(final String cleanNumbers) {
        final String[] parts = cleanNumbers.split("-");
        final Integer headPart = lookup.get(parts[0]);
        final Integer tailPart = lookup.get(parts[1]);
        return headPart + tailPart;
    }
}