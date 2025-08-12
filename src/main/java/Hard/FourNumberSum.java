package Hard;

import java.util.*;

/**
 * Write a function that takes in a non-empty array of distinct integers and an
 * integer representing a target sum. The function should find all quadruplets in
 * the array that sum up to the target sum and return a two-dimensional array of
 * all these quadruplets in no particular order.
 *
 * If no four numbers sum up to the target sum, the function should return an
 * empty array.
 *
 * SAMPLE INPUT:
 * array = [7, 6, 4, -1, 1, 2]
 * targetSum = 16
 *
 * SAMPLE OUTPUT:
 * [[7, 6, 4, -1], [7, 6, 1, 2]] // the quadruplets could be ordered differently
 */

public class FourNumberSum {
    public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
        // DESC: Initialize necessary variables
        int arrayLength = array.length;
        int pairTargetDifference = 0;
        List<Integer[]> validQuadruplets = new ArrayList<Integer[]>();
        Map<Integer, ArrayList<Integer[]>> tableOfPairs = new HashMap<>();
        ArrayList<Integer[]> parentArray = new ArrayList<>();
        int numberOfMatchingPairs = 0;
        int posA = 0;
        int posB = 0;
        int posC = 0;
        int posD = 0;
        int pairKey = 0;

        for (int cN = 0; cN < arrayLength; cN++) {
            // NOTE: The current number, which is what the two
            // ... subsequent cursors base their movement on

            for (int rC = cN + 1; rC < arrayLength; rC++) {
                // NOTE: The right-cursor loop (i.e., moves from
                // ... the current number (exclusive) to the end
                // ... of the array

                pairTargetDifference = targetSum - (array[cN] + array[rC]);
                // DESC: Check if `pairTargetDifference` is in
                // ... `tableOfPairs`
                if (tableOfPairs.containsKey(pairTargetDifference)) {
                    // DESC: Output quadruplet to `validQuadruplets`
                    // NOTE: `pairTargetDifference` (key) exists
                    // ... in the HashMap meaning that the opposing
                    // ... value determined by the left-cursor is found
                    // ... and we will have a valid quadruplet
                    numberOfMatchingPairs = tableOfPairs.get(pairTargetDifference).size();
                    posA = array[cN];
                    posB = array[rC];

                    // DESC: Loop through available value(s) associated
                    // ... with the HashMap key then add to the validQuadruplets
                    // ... array
                    for (int i = 0; i < numberOfMatchingPairs; i++) {
                        posC = tableOfPairs.get(pairTargetDifference).get(i)[0];
                        posD = tableOfPairs.get(pairTargetDifference).get(i)[1];
                        if ((posA + posB + posC + posD) == targetSum) {
                            validQuadruplets.add(new Integer[]{posC, posD, posA, posB});
                        }
                    }
                }
            }
            for (int lC = 0; lC < cN; lC++) {
                // DESC: Add the key-value pair-integer and pair-array
                // ... to the `tableOfPairs`
                // NOTE: The left-cursor loop (i.e., moves from
                // ... the start of the array to the current
                // ... number (exclusive)
                pairKey = array[lC] + array[cN];
                parentArray.add(new Integer[]{array[lC], array[cN]});
                tableOfPairs.put(pairKey, parentArray);
            }
        }

        return validQuadruplets;
    }
}