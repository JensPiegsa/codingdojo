package array.diff;

public class ArrayOperations {

  public static int[] arrayDiff(int[] a, int[] b) {

    if (b.length == 0 || a.length == 0) {
      return a;
    }

    int[] result = new int[a.length];
    int resultPos = 0;

    for (int indexA = 0; indexA < a.length; indexA++) {

      for (int indexB = 0; indexB < b.length; indexB++) {
        if (a[indexA] == b[indexB]) {
          break;
        }
        if (indexB + 1 == b.length) {
          result[resultPos] = a[indexA];
          resultPos++;
        }
      }
    }

    int[] result2 = new int[resultPos];

    System.arraycopy(result,0, result2, 0, resultPos);

    return result2;
  }
}