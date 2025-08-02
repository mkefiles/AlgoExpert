package Medium;

import java.util.*;
import java.lang.Math;

/**
 * Write a function that takes in two non-empty arrays of integers, finds the
 * pair of numbers (one from each array) whose absolute difference is closest to
 * zero, and returns an array containing these two numbers, with the number from
 * the first array in the first position.
 *
 * Note that the absolute difference of two integers is the distance between
 * them on the real number line. For example, the absolute difference of -5 and 5
 * is 10, and the absolute difference of -5 and -4 is 1.
 *
 * You can assume that there will only be one pair of numbers with the smallest
 * difference.
 *
 * SAMPLE INPUT:
 * arrayOne = [-1, 5, 10, 20, 28, 3]
 * arrayTwo = [26, 134, 135, 15, 17]
 *
 * SAMPLE OUTPUT:
 * [28, 26]
 */

public class SmallestDifference {
    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        // DESC: Initialize necessary variables
        int arrayOneIndex = 0;
        int arrayTwoIndex = 0;
        int[] output = new int[2];
        boolean flag = false;
        int differenceBetweenValues = Integer.MAX_VALUE;
        int arrayOneLength = arrayOne.length;
        int arrayTwoLength = arrayTwo.length;

        // DESC: Sort arrays in ASC. order (in-place; destructive)
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);

        // DESC: Loop through arrays until flag thrown
        while (flag != true) {
            // DESC: Compare values at indices in both arrays
            if (arrayOne[arrayOneIndex] == arrayTwo[arrayTwoIndex]) {
                // NOTE: Values are equal so no further work is necessary
                // DESC: Update 'solution-pair'
                output[0] = arrayOne[arrayOneIndex];
                output[1] = arrayTwo[arrayTwoIndex];

                // DESC: Throw flag (to kill loop)
                flag = true;
            } else if (arrayOne[arrayOneIndex] < arrayTwo[arrayTwoIndex]) {
                // NOTE: Value at 'arrayOne' index is less-than 'arrayTwo' value

                // DESC: Check if current difference is less-than prior difference
                if (Math.abs(arrayOne[arrayOneIndex] - arrayTwo[arrayTwoIndex]) < differenceBetweenValues) {
                    // DESC: Update difference
                    differenceBetweenValues = Math.abs(arrayOne[arrayOneIndex] - arrayTwo[arrayTwoIndex]);
                    // DESC: Update 'solution-pair'
                    output[0] = arrayOne[arrayOneIndex];
                    output[1] = arrayTwo[arrayTwoIndex];
                }
                if (arrayOneIndex == arrayOneLength - 1) {
                    // DESC: Throw flag (to kill loop)
                    flag = true;
                } else {
                    // DESC: Increment arrayOne index value
                    arrayOneIndex = arrayOneIndex + 1;
                }
            } else {
                // NOTE: Value at 'arrayOne' index is greater-than 'arrayTwo' value

                // DESC: Check if current difference is less-than prior difference
                if (Math.abs(arrayOne[arrayOneIndex] - arrayTwo[arrayTwoIndex]) < differenceBetweenValues) {
                    // DESC: Update difference
                    differenceBetweenValues = Math.abs(arrayOne[arrayOneIndex] - arrayTwo[arrayTwoIndex]);
                    // DESC: Update 'solution-pair'
                    output[0] = arrayOne[arrayOneIndex];
                    output[1] = arrayTwo[arrayTwoIndex];
                }
                if (arrayTwoIndex == arrayTwoLength - 1) {
                    // DESC: Throw flag (to kill loop)
                    flag = true;
                } else {
                    // DESC: Increment arrayTwo index value
                    arrayTwoIndex = arrayTwoIndex + 1;
                }
            }
        }
        return output;
    }
}
