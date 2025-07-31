package Easy;

import java.util.*;

public class NonConstructibleChange {
    /**
     * Given an array of positive integers representing the values of coins in your
     * possession, write a function that returns the minimum amount of change (the
     * minimum sum of money) that you CANNOT create. The given coins can have
     * any positive integer value and aren't necessarily unique (i.e., you can have
     * multiple coins of the same value).
     *
     * For example, if you're given `coins = [1, 2, 5]`, the minimum amount of change
     * that you can't create is `4`. If you're given no coins, the minimum amount of
     * change that you can't create is `1`.
     *
     * SAMPLE INPUT:
     * coins = [5, 7, 1, 1, 2, 3, 22]
     *
     * SAMPLE OUTPUT:
     * 20
     */

    /**
     * Each 'coin' in 'coins' represents the value that that coin
     * ... is worth (note: these values do NOT follow the American
     * ... currency (i.e., a coin can be in the value of '3'))
     * T.C.: O(n log(n))
     * S.C.: O(1) || O(n) [sorted in place || a copy of the sorted array made]
     */

    public int nonConstructibleChange(int[] coins) {
        // DESC: Initialize necessary variables
        int coinArrayLength = coins.length;
        int[] sortedCoinsArray = new int[coinArrayLength];
        int sumOfChange = 0;

        // DESC: Get rid of instances where `coins` is empty or has single value
        if (coinArrayLength == 0) {
            return 1;
        }
        else if (coinArrayLength == 1) {
            if (coins[0] != 1) {
                return 1;
            } else {
                return 2;
            }
        }

        // DESC: Sort the 'coins' array (make copy to not be destructive)
        for (int i = 0; i < coinArrayLength; i++) {
            sortedCoinsArray[i] = coins[i];
        }
        Arrays.sort(sortedCoinsArray);

        // DESC: Loop through array
        for (int i = 0; i < coinArrayLength; i++) {
            // NOTE: If the value of the current coin is greater-than
            // ... all of the preceding coins summed up PLUS ONE, then
            // ... the value that CANNOT be obtained is the value of the
            // ... summed up preceding coins plus one.
            if (sortedCoinsArray[i] > (sumOfChange + 1)) {
                return sumOfChange + 1;
            } else {
                sumOfChange = sumOfChange + sortedCoinsArray[i];
            }
        }

        // NOTE: We made it through the entire list thus the lowest
        // ... value we CANNOT create is the overall sum plus one
        return sumOfChange + 1;
    }
}

/**
 * ALTERNATIVE SOLUTION:
 * Loop through every numeric possibility from 1 to the
 * ... maximum value of all 'coins' added then check the
 * ... provided array (and all summations) against each
 * ... value (incredibly inefficient)
 */