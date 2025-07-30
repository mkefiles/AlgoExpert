package Easy;

/**
 * Write a function that takes in a non-empty array of distinct integers and an
 * integer representing a target sum. If any two numbers in the input array sum
 * up to the target sum, the function should return them in an array, in any
 * order. If no two numbers sum up to the target sum, the function should return
 * an empty array.
 *
 * Note that the target sum has to be obtained by summing two different integers
 * in the array; you can't add a single integer to itself in order to obtain the
 * target sum.
 *
 * You can assume that there will be at most one pair of numbers summing up to
 * the target sum.
 *
 * SAMPLE INPUT:
 * array = [3, 5, -4, 8, 11, 1, -1, 6];
 * targetSum = 10;
 *
 * SAMPLE OUTPUT:
 * [-1, 11] // the numbers could be in reverse order
 */

public class TwoNumberSum {
    public static int[] twoNumberSum(int[] array, int targetSum) {
        // DESC: Initialize necessary variables
        int arrLength = array.length;
        int[] sumArray = {0, 0};

        // DESC: Remove scenarios where length is less-than 2
        if (arrLength < 2) {
            return new int[0];
        } else {
            // DESC: Loop over array
            for (int i = 0; i < arrLength; i++) {
                for (int j = 0; j < arrLength; j++) {
                    // DESC: Do not add the same number to itself
                    if (i == j) {
                        continue;
                    } else {
                        int currentSum = array[i] + array[j];
                        if (currentSum == targetSum) {
                            sumArray[0] = array[i];
                            sumArray[1] = array[j];
                            return sumArray;
                        }
                    }
                }
            }
        }
        return new int[0];
    }
}