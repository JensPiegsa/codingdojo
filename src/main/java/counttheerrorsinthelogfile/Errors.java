package counttheerrorsinthelogfile;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Errors {

  public static final String ERROR = "ERROR: ";

  public static LinkedHashMap<String, Integer> mapErrors() {

    LinkedHashMap<String, Integer> result = new LinkedHashMap<>();

      try (BufferedReader br = new BufferedReader(new FileReader("server.log"))) {

        for (String line; (line = br.readLine()) != null; ) {

          if (line.startsWith(ERROR)) {
            String errorName = line.substring(ERROR.length()).toLowerCase();

            result.compute(errorName, (key,value) -> (value == null) ? 1 : value + 1);
          }
        }

      } catch (IOException e) {
          throw new RuntimeException(e);
      }

      return result.entrySet().stream()
              .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }
}