package CIQ_Arrays.Hard;

/**
 * Write a function that takes in an array of at least two integers and that
 * returns an array of the starting and ending indices of the smallest subarray
 * in the input array that needs to be sorted in place in order for the entire
 * input array to be sorted (in ascending order).
 *
 * If the input array is already sorted, the function should return `[-1, -1]`
 *
 * SAMPLE INPUT:
 * array = [1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19]
 *
 * lowerIndex = 5
 * upperIndex = 6 ... 7 ... 8
 *
 * SAMPLE OUTPUT:
 * [3, 9]
 */

public class SubarraySort {
    public static int[] subarraySort(int[] array) {
        // DESC: Initialize necessary variables
        int arrayLength = array.length;
        int unsortedLowValueIndex = -1;
        int unsortedHighValueIndex = -1;
        int lowestValue = Integer.MAX_VALUE;
        int highestValue = Integer.MIN_VALUE;
        int[] outputArray = {-1, -1};
        int currentNumber = 0;
        int newLowValueIndex = -1;
        int newHighValueIndex = -1;
        boolean hasBeenUpdated = false;

        // DESC: Loop 01: Determine the first AND last
        // ... occurrences of the unsorted values
        for (int cI = 0; cI < arrayLength; cI++) {
            currentNumber = array[cI];
            // DESC: Determine which index to update
            if (unsortedLowValueIndex == -1) {
                // NOTE: If the low-value index is '-1'
                // ... then that means it has not yet
                // ... been updated (therefore it has
                // ... not yet been encountered
                // DESC: Determine if a value is unsorted
                if (cI == 0) {
                    // NOTE: If the currentIndex is '0'
                    // ... then it is only necessary to
                    // ... check the right-adjacent value
                    if (currentNumber > array[cI + 1]) {
                        unsortedLowValueIndex = cI;
                    }
                } else if (cI == arrayLength - 1) {
                    // NOTE: If the currentIndex is at
                    // ... the end of the array, then it
                    // ... is only necessary to check the
                    // ... left-adjacent value
                    if (currentNumber < array[cI - 1]) {
                        unsortedLowValueIndex = cI;
                    }
                } else {
                    // NOTE: Otherwise, it is safe to
                    // ... check both the left and right
                    // ... adjacent values
                    if (currentNumber < array[cI - 1] || currentNumber > array[cI + 1]) {
                        unsortedLowValueIndex = cI;
                    }
                }
            } else {
                // NOTE: Low-value index should have been
                // ... updated so we can work on updating
                // ... the high-value index (it will need
                // ... to continue updating until the end
                // ... of the array)
                // DESC: Determine if a value is unsorted
                if (cI == 0) {
                    // NOTE: If the currentIndex is '0'
                    // ... then it is only necessary to
                    // ... check the right-adjacent value
                    if (currentNumber > array[cI + 1]) {
                        unsortedHighValueIndex = cI;
                    }
                } else if (cI == arrayLength - 1) {
                    // NOTE: If the currentIndex is at
                    // ... the end of the array, then it
                    // ... is only necessary to check the
                    // ... left-adjacent value
                    if (currentNumber < array[cI - 1]) {
                        unsortedHighValueIndex = cI;
                    }
                } else {
                    // NOTE: Otherwise, it is safe to
                    // ... check both the left and right
                    // ... adjacent values
                    if (currentNumber < array[cI - 1] || currentNumber > array[cI + 1]) {
                        unsortedHighValueIndex = cI;
                    }
                }
            }
        }

        // DESC: If the low/high-value indices have not
        // ... been updated, then the program should
        // ... prematurely end
        if (unsortedLowValueIndex == -1 || unsortedHighValueIndex == -1) {
            return outputArray;
        }

        // DESC: If the start-index is greater-than the
        // ... end-index, then the entire array must be
        // ... sorted
        if (array[0] > array[arrayLength - 1]) {
            outputArray[0] = 0;
            outputArray[1] = arrayLength - 1;
            return outputArray;
        }

        // DESC: Loop 02: Determine the lowest value AND
        // ... the highest values in the sub-array
        for (int cI = unsortedLowValueIndex; cI <= unsortedHighValueIndex; cI++) {
            currentNumber = array[cI];
            if (currentNumber > highestValue) {
                highestValue = currentNumber;
            }
            if (currentNumber < lowestValue) {
                lowestValue = currentNumber;
            }
        }

        // DESC: Loop 03: Determine the index where the
        // ... lowest value is lower then the index-value
        // ... where the highest value is higher than
        // ... the index value
        for (int cI = 0; cI < arrayLength; cI++) {
            currentNumber = array[cI];
            // DESC: Get the index value for the `lowestValue`
            // NOTE: The program needs to check if it is the 0th
            // ... index because, when checking the `cI + 1`, it
            // ... is always skipping the 0th index and, without
            // ... the ability to go into a negative index you
            // ... need a special branch where the logic changes
            // ... for the first value
            // NOTE: A boolean-flag is used because we want to
            // ... prevent subsequent updates as that is throwing
            // ... false output when there are higher values scattered
            // ... later in the array
            if (cI == 0) {
                if ((array[cI] > lowestValue) && (hasBeenUpdated == false)) {
                    newLowValueIndex = cI;
                    hasBeenUpdated = true;
                }
            }
            if (cI < (arrayLength - 2)) {
                if ((array[cI + 1] > lowestValue) && (hasBeenUpdated == false)) {
                    newLowValueIndex = cI + 1;
                    hasBeenUpdated = true;
                }
            }

            // DESC: Get the index value for the `highestValue`
            if (highestValue > currentNumber) {
                newHighValueIndex = cI;
            }
        }
        outputArray[0] = newLowValueIndex;
        outputArray[1] = newHighValueIndex;
        return outputArray;
    }
}