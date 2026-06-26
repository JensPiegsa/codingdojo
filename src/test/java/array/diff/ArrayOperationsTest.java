package array.diff;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class ArrayOperationsTest {

    // https://www.codewars.com/kata/523f5d21c841566fde000009/train/java

    @Test
    public void sampleTests() {
        assertArrayEquals(new int[] {2}, ArrayOperations.arrayDiff(new int [] {1,2}, new int[] {1}));
        assertArrayEquals(new int[] {2,2}, ArrayOperations.arrayDiff(new int [] {1,2,2}, new int[] {1}));
        assertArrayEquals(new int[] {1}, ArrayOperations.arrayDiff(new int [] {1,2,2}, new int[] {2}));
        assertArrayEquals(new int[] {1,2,2}, ArrayOperations.arrayDiff(new int [] {1,2,2}, new int[] {}));
        assertArrayEquals(new int[] {}, ArrayOperations.arrayDiff(new int [] {}, new int[] {1,2}));
    }

}