package Easy;

import java.util.*;

/**
 * Given two non-empty arrays of integers, write a function that determines
 * whether the second array is a subsequence of the first one.
 *
 * A subsequence of an array is a set of numbers that aren't necessarily adjacent
 * in the array but that are in the same order as they appear in the array. For
 * instance, the numbers [1, 3, 4] form a subsequence of the array
 * [1, 2, 3, 4], and so do the numbers [2, 4]. Note that a single number in
 * an array and the array itself are both valid subsequences of the array.
 *
 * SAMPLE INPUT:
 * array = [5, 1, 22, 25, 6, -1, 8, 10]
 * sequence = [1, 6, -1, 10]
 *
 * SAMPLE OUTPUT:
 * true
 */


public class ValidateSubsequence {
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        // DESC: Initialize necessary variables
        int arrayLength = array.size();
        int sequenceLength = sequence.size();
        boolean[] resultsArray = new boolean[sequenceLength];
        int cursorLocation = 0;

        // DESC: If sequence is longer than array, it cannot work
        if (sequenceLength > arrayLength) {
            return false;
        }

        // DESC: Loop over 'sequence'
        for (int i = 0; i < sequenceLength; i++) {
            for (int j = cursorLocation; j < arrayLength; j++) {
                if (sequence.get(i) == array.get(j)) {
                    // DESC: Match found; Update 'resultsArray'
                    resultsArray[i] = true;

                    // DESC: Update cursorLocation (to avoid rechecking invalid numbers)
                    cursorLocation = j + 1;

                    // DESC: Proceed to next iteration of outer loop
                    break;
                }
            }
        }

        // DESC: Check 'resultsArray' for 'false' values
        for (int i = 0; i < sequenceLength; i++) {
            if (resultsArray[i] == false) {
                return false;
            }
        }

        // DESC: Sequence IS in Array
        return true;
    }
}
