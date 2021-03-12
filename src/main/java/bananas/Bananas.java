package bananas;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

import java.util.*;

public class Bananas {

  static Set<String> bananas(final String inputString) {
    return recursiveBanana(inputString, "banana");
  }

  private static Set<String> recursiveBanana(String newInputString, String searchTerm) {
    if (searchTerm.isEmpty()) {
      if (!newInputString.isEmpty()) {
        String tail = nDashes(newInputString.length());
        return singleton(tail);
      }
      return singleton("");
    }
    char firstLetter = searchTerm.charAt(0);
    int position = newInputString.indexOf(firstLetter);
    Set<String> result = new HashSet<>();
    if (position == -1) {
      return emptySet();
    }
    while (position != -1) {
      String head = nDashes(position) + firstLetter;
      Set<String> subResult = recursiveBanana(newInputString.substring(position + 1),
          searchTerm.substring(1));
      for (String tail : subResult) {
        String fullWord = head + tail;
        result.add(fullWord);
      }
      position = newInputString.indexOf(firstLetter, position + 1);
    }
    return result;
  }

  private static String nDashes(int n) {
    return String.join("", Collections.nCopies(n, "-"));
  }
}
