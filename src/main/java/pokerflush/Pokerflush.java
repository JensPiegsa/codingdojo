package pokerflush;

import java.util.Arrays;

public class Pokerflush {
  public static boolean CheckIfFlush(String[] cards) {
    return Arrays.stream(cards)
        .map(Pokerflush::extractLastCharacter)
        .distinct()
        .count() == 1;
  }

  private static String extractLastCharacter(String card) {
    return card.substring(card.length() - 1);
  }
}