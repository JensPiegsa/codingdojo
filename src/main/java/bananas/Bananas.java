package bananas;

import java.util.*;
import java.util.stream.Collectors;

public class Bananas {

  static Set<String> bananas(final String inputString) {
    return recursiveBanana(inputString, "banana");
  }

  private static Set<String> recursiveBanana(String newInputString, String searchTerm) {
    // Abbruchbedingung definieren
    // Suche Positionen p des ersten Buchstaben von searchTerm in newInputString
    // Resultset definieren
    // Schleife durchläuft die Positionen
    //    Aufruf recursiveBanana mit: newInputString ab p+1 mit searchTerm ohne ersten Buchstaben
    //    Schleife durchläuft das result von recursiveBanana
    //      Head + Tail definieren
    //      Head + Tail dem Resultset hinzufügen
    // Return Resultset
    return Collections.EMPTY_SET;
  }

}
