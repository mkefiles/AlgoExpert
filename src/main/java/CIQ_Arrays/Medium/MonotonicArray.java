package CIQ_Arrays.Medium;

/**
 * Write a function that takes in an array of integers and returns a boolean
 * representing whether the array is monotonic.
 *
 * An array is said to be monotonic if its elements, from left to right, are
 * entirely non-increasing or entirely non-decreasing.
 *
 * Non-increasing elements aren't necessarily exclusively decreasing; they simply
 * don't increase. Similarly, non-decreasing elements aren't necessarily
 * exclusively increasing; they simply don't decrease.
 *
 * Note that empty arrays and arrays of one element are monotonic.
 *
 * SAMPLE INPUT:
 * array = [-1, -5, -10, -1100, -1100, -1101, -1102, -9001]
 *
 * SAMPLE OUTPUT:
 * true
 */

/**
 * ADDITIONAL NOTES:
 * Put simply, a 'monotonic' array is an array that is either consistently
 * increasing OR consistently decreasing. The difference, and justification
 * for the sophisticated word, is that back-to-back duplicate value(s) are
 * allowed without breaking the monotonic nature.
 */

public class MonotonicArray {
    public static boolean isMonotonic(int[] array) {
        // DESC: Initialize upfront variables
        int arrayLength = array.length;
        boolean isMonotonic = true;

        // DESC: Check if the array is empty OR one element
        if ((arrayLength == 0) || (arrayLength == 1)) {
            return isMonotonic;
        }

        // DESC: Initialize post-test variables
        enum Direction {
            INCREASING,
            DECREASING,
            STABLE
        }
        int leftCursorIndex = 0;
        int rightCursorIndex = arrayLength - 1;
        int leftCursorValue = array[leftCursorIndex];
        int rightCursorValue = array[rightCursorIndex];

        // DESC: Determine proposed direction
        Direction proposedDirection = (leftCursorValue < rightCursorValue)
                ? Direction.INCREASING : (leftCursorValue > rightCursorValue)
                ? Direction.DECREASING : Direction.STABLE;

        // DESC: Loop through array and use `proposedDirection`
        // ... to determine if it stays consistent
        for (int incrementer = 0; incrementer < arrayLength; incrementer++) {
            if (proposedDirection == Direction.INCREASING) {
                if (array[incrementer] < leftCursorValue) {
                    isMonotonic = false;
                    break;
                } else {
                    leftCursorValue = array[incrementer];
                }
            }

            if (proposedDirection == Direction.DECREASING) {
                if (array[incrementer] > leftCursorValue) {
                    isMonotonic = false;
                    break;
                } else {
                    leftCursorValue = array[incrementer];
                }
            }

            if (proposedDirection == Direction.STABLE) {
                if (array[incrementer] != leftCursorValue) {
                    isMonotonic = false;
                    break;
                } else {
                    leftCursorValue = array[incrementer];
                }
            }
        }

        // DESC: Assuming loop-logic did not flip the value,
        // ... it should always return true
        return isMonotonic;
    }
}
