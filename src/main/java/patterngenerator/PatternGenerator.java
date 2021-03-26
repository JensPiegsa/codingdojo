package patterngenerator;

import java.lang.String;

public class PatternGenerator {

  public static String patternGenerator(int c) {
    if (c <= 0) {
      return "";
    }
    StringBuilder result = new StringBuilder();
    if (isOdd(c)) {
      result.append(buildLine(c));
    } else {
      result.append(buildLine(c / 2));
      result.append(" ");
      result.append(buildLine(c / 2).reverse());
    }
    return result.toString();
  }

  private static StringBuilder buildLine(int n) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < n; i++) {
      if (i == 0) {
        result.append("x");
      } else {
        result.append(" ").append(isOdd(i) ? "o" : "x");
      }
    }
    return result;
  }

  private static boolean isOdd(int c) {
    return c % 2 > 0;
  }
}