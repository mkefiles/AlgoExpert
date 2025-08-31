package CIQ_Arrays.Easy;

import java.util.*;

/**
 * Write a function that takes in a non-empty array of integers that are sorted
 * in ascending order and returns a new array of the same length with the squares
 * of the original integers also sorted in ascending order.
 *
 * SAMPLE INPUT:
 * array = [1, 2, 3, 5, 6, 8, 9]
 * SAMPLE OUTPUT:
 * array = [1, 2, 3, 5, 6, 8, 9]
 */

public class SortedSquaredArray {
    public int[] sortedSquaredArray(int[] array) {
        // DESC: Initialize necessary variables
        int arrayLength = array.length;
        int[] squaredArray = new int[arrayLength];

        // DESC: Loop through, square values and append to array
        for (int i = 0; i < arrayLength; i++) {
            squaredArray[i] = array[i] * array[i];
        }

        // DESC: Sort values (to handle negative number input)
        // NOTE: This is an example of a Bubble Sort algorithm
        for (int i = 0; i < arrayLength; i++) {
            for (int j = 0; j < arrayLength - 1; j++) {
                int firstVal = squaredArray[j];
                int secondVal = squaredArray[j+1];
                if (firstVal > secondVal) {
                    squaredArray[j] = secondVal;
                    squaredArray[j+1] = firstVal;
                }
            }
        }

        return squaredArray;
    }
}

/**
 * ALTERNATIVE SOLUTION:
 * With the input-array being sorted, it is possible to take
 * ... the lowest (negative) value and the highest (positive)
 * ... value, compare the absolute value of both, square the
 * ... greater number and add that value to the new array.
 * ... Then, simply move the cursor of the inwards from the
 * ... value that was just squared.
 *
 */