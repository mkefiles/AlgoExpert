package Medium;

import java.util.*;

/**
 * Write a function that takes in a non-empty array of distinct integers and an
 * integer representing a target sum. The function should find all triplets in
 * the array that sum up to the target sum and return a two-dimensional array of
 * all these triplets. The numbers in each triplet should be ordered in ascending
 * order, and the triplets themselves should be ordered in ascending order with
 * respect to the numbers they hold.
 *
 * If no three numbers sum up to the target sum, the function should return an
 * empty array.
 *
 * SAMPLE INPUT:
 * array = [12, 3, 1, 2, -6, 5, -8, 6]
 * targetSum = 0
 *
 * SAMPLE OUTPUT:
 * [[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]
 */

public class ThreeNumberSum {
    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        // DESC: Initialize necessary variables
        // NOTE: Algorithm: cS = cN + L + R
        int arrayLength = array.length;
        int currentSum = 0;
        int currentNumber = 0;
        boolean hasTriedAllValues = false;
        int leftCursor = 0;
        int rightCursor = 0;
        ArrayList<Integer[]> output = new ArrayList<Integer[]>();

        // DESC: Sort the Array (using built-in method; destructive)
        // NOTE: In theory, if sorted upfront then all subsequent
        // ... output should be sorted as well because an Array List
        // ... maintains the insertion order
        Arrays.sort(array);

        System.out.println("Sorted Array: " + Arrays.toString(array));

        // DESC: Increment through array for 'cN'
        for (int i = 0; i < arrayLength - 1; i++) {
            // DESC: Set location of 'cN' and defaults for 'L' and 'R'
            int currentLIndex = i + 1;
            int currentRIndex = arrayLength - 1;
            currentNumber = array[i];

            // DESC: Reset flag to 'false' between iterations
            hasTriedAllValues = false;

            // DESC: Move cursors for 'L' and 'R'
            while (hasTriedAllValues == false) {
                // DESC: Set location of 'L' and 'R' cursors
                leftCursor = array[currentLIndex];
                rightCursor = array[currentRIndex];

                // DESC: Check if 'L' and 'R' cursors have crossed
                // ... If so, then end the loop
                if (currentLIndex >= currentRIndex) {
                    hasTriedAllValues = true;
                } else {

                    // DESC: Determine 'currentSum'
                    currentSum = currentNumber + leftCursor + rightCursor;

                    if (currentSum == targetSum) {
                        // DESC: Append values to 'tripletSequence' then that array to 'output'
                        output.add(new Integer[]{currentNumber, leftCursor, rightCursor});

                        // DESC: Move 'L' and 'R' cursors simultaneously
                        currentLIndex = currentLIndex + 1;
                        currentRIndex = currentRIndex - 1;

                    } else if (currentSum < targetSum) {
                        // DESC: Move 'L' cursor
                        currentLIndex = currentLIndex + 1;

                    } else {
                        // DESC: Move 'R' cursor
                        currentRIndex = currentRIndex - 1;

                    }
                }
            }
        }
        return output;
    }
}
