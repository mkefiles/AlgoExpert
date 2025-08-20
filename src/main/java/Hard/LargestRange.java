package Hard;

import java.util.HashMap;

/**
 * Write a function that takes in an array of integers and returns an array of
 * length 2 representing the largest range of integers contained in that array.
 * <p>
 * The first number in the output array should be the first number in the range,
 * while the second number should be the last number in the range.
 * <p>
 * A range of numbers is defined as a set of numbers that come right after each
 * other in the set of real integers. For instance, the output array
 * `[2, 6]` represents the range `{2, 3, 4, 5, 6}`, which is a range of length 5.
 * Note that numbers don't need to be sorted or adjacent in the input array in
 * order to form a range.
 * <p>
 * You can assume that there will only be one largest range.
 * <p>
 * SAMPLE INPUT:
 * array = [1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6]
 * <p>
 * SAMPLE OUTPUT:
 * [0, 7]
 */

public class LargestRange {
    public static int[] largestRange(int[] array) {
        // DESC: Initialize necessary variables
        int arrayLength = array.length;
        HashMap<Integer, Boolean> numberCheckedMap = new HashMap<Integer, Boolean>();
        int[] outputArray = new int[2];
        int rangeLowerValue = 0;
        int rangeHigherValue = 0;
        int rangeValuesDifference = 0;

        // DESC: Remove scenarios where arrayLength is '1'
        if (arrayLength == 1) {
            outputArray[0] = array[0];
            outputArray[1] = array[0];
            return outputArray;
        }

        // DESC: Loop 01: Add value(s) to the HashMap
        // NOTE: Duplicate key names are not allowed so
        // ... there is no need to check if a value exists
        for (int cI = 0; cI < arrayLength; cI++) {
            numberCheckedMap.put(array[cI], false);
        }

        // DESC: Loop 02: Loop through input array and
        // ... for each number, check all values on the
        // ... left side and the right side to see if they
        // ... exist in the HashMap
        for (int cI = 0; cI < arrayLength; cI++) {
            rangeLowerValue = array[cI];
            rangeHigherValue = array[cI];

            // DESC: Determine if the current value, of the
            // ... array, has been checked in the HashMap yet
            if (numberCheckedMap.get(array[cI]) == false) {
                // NOTE: This means that the value at currentIndex
                // ... has not been checked so we can proceed to
                // ... check it
                // DESC: Check numeric values to the left (i.e.,
                // ... values decrementing by '1')
                while (true) {
                    // DESC: Check if value exists in HashMap
                    if (numberCheckedMap.get(rangeLowerValue) == null) {
                        // NOTE: This means the value does not exist
                        // ... the loop can stop iterating
                        break;
                    } else {
                        numberCheckedMap.put(rangeLowerValue, true);
                        rangeLowerValue = rangeLowerValue - 1;

                    }
                }

                // DESC: Check numeric values to the right (i.e.,
                // ... values incrementing by '1')
                while (true) {
                    // DESC: Check if value exists in HashMap
                    if (numberCheckedMap.get(rangeHigherValue) == null) {
                        // NOTE: This means the value does not exist
                        // ... the loop can stop iterating
                        break;
                    } else {
                        numberCheckedMap.put(rangeHigherValue, true);
                        rangeHigherValue = rangeHigherValue + 1;
                    }
                }

                // DESC: Check if existing output range is smaller
                // ... than the proposed updated range
                // NOTE: The rangeHigherValue / rangeLowerValue has '1'
                // ... being decremented / incremented by '1' because
                // ... I have the logic commiting the values to the
                // ... output array AFTER decrementing / incrementing
                // ... them so it was always off by one and handling
                // ... the math below was the easiest way to negate that
                rangeValuesDifference = (rangeHigherValue - 1) - (rangeLowerValue + 1);
                if ((outputArray[1] - outputArray[0]) < rangeValuesDifference) {
                    // NOTE: If the existing output range IS smaller,
                    // ... then update it for the new range
                    outputArray[0] = rangeLowerValue + 1;
                    outputArray[1] = rangeHigherValue - 1;
                }

            } else {
                // NOTE: The value has been checked so it can be
                // ... skipped (this helps to ensure optimal time
                // ... complexity because we are not checking a
                // ... value multiple times)
                continue;
            }
        }
        return outputArray;
    }
}
