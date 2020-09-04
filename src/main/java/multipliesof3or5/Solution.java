package multipliesof3or5;

import java.util.HashSet;
import java.util.Set;

public class Solution {

  public int solution(int number) {
    
    if (number < 0) {
      return 0;
    }

    final Set<Integer> threeMultiples = checkForMultiples(3, number);
    final Set<Integer> fiveMultiples = checkForMultiples(5, number);

    fiveMultiples.addAll(threeMultiples);
    return fiveMultiples.stream().mapToInt(Integer::intValue).sum();
  }

  private Set<Integer> checkForMultiples(final int multiplier, final int number) {
    Set<Integer> allMultiples = new HashSet<>();
    
    for (int value = multiplier; value < number ; value = value + multiplier) {
      allMultiples.add(value);  
    }
    
    return allMultiples;
  }
}