package bananas;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

import java.util.*;

public class Bananas {

  private static final int notFound = -1;

  static Set<String> bananas(final String input) {
    return recursiveBanana(input, "banana");
  }

  private static Set<String> recursiveBanana(String remainingInput, String searchTerm) {
    if (searchTerm.isEmpty()) {
      String tail = nDashes(remainingInput.length());
      return singleton(tail);
    }
    char firstLetter = searchTerm.charAt(0);
    int position = remainingInput.indexOf(firstLetter);
    Set<String> result = new HashSet<>();
    
    while (position != notFound) {
      String head = nDashes(position) + firstLetter;
      Set<String> tails = recursiveBanana(remainingInput.substring(position + 1), searchTerm.substring(1));
      for (String tail : tails) {
        String fullWord = head + tail;
        result.add(fullWord);
      }
      position = remainingInput.indexOf(firstLetter, position + 1);
    }
    return result;
  }

  private static String nDashes(int n) {
    return String.join("", Collections.nCopies(n, "-"));
  }
}
