package supermarketqueue;

import java.util.Arrays;

public class Solution {

    public static int solveSuperMarketQueue(final int[] customerTimesInMinutes, final int numberOfTills) {
        if (customerTimesInMinutes.length > 0) {
            int[] totalMinutes = new int[numberOfTills];

            for (final int customerMinutes : customerTimesInMinutes) {
                int index = findMinIndex(totalMinutes);
                totalMinutes[index] += customerMinutes;
            }
            return Arrays.stream(totalMinutes).max().getAsInt();
        }
        return 0;
    }

    private static int findMinIndex(final int[] totalMinutes) {
        int minimum = Integer.MAX_VALUE;
        int minimumIndex = -1;

        for (int i = 0; i < totalMinutes.length; i++) {
            if (totalMinutes[i] <= minimum) {
                minimum = totalMinutes[i];
                minimumIndex = i;
            }
        }
        return minimumIndex;
    }

}