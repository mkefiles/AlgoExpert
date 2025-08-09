package Medium;

import java.lang.Math;

/**
 * Given an array of integers between `1` and `n`, inclusive, where `n` is
 * the length of the array, write a function that returns the first integer
 * that appears more than once (when the array is read from left to right).
 *
 * In other words, out of all the integers that might occur more than once in the
 * input array, your function should return the one whose first duplicate value
 * has the minimum index.
 *
 * If no integer appears more than once, your function should return `-1`.
 *
 * Note that you're allowed to mutate the input array.
 *
 * SAMPLE INPUT:
 * array = [2, 1, 5, 2, 3, 3, 4]
 *
 * SAMPLE OUTPUT:
 * 2 // 2 is the first integer that appears more than once.
 * // 3 also appears more than once, but the second 3 appears after the second 2.
 */

public class FirstDuplicateValue {
    public int firstDuplicateValue(int[] array) {
        // DESC: Initialize necessary values
        int arrayLength = array.length;
        int duplicateValue = -1;
        int negativeCheckValueIndex = -1;
        int negativeCheckValue = 0;

        // DESC: Loop over array
        // NOTE: Algorithm used is `value - 1 = index`
        for (int i = 0; i < arrayLength; i++) {
            // DESC: Get absolute value in the event that
            // ... the value at `i` is negative
            negativeCheckValueIndex = Math.abs(array[i]) - 1;

            // DESC: Check if the value at `negativeCheckValueIndex`
            // ... is a negative number
            // NOTE: A negative number indicates that a duplicate number
            // ... exists because it means that it was previously flipped
            // ... to negative when the instructions indicate that the
            // ... values, in the array, will be between 1 and n with n
            // ... being the length of the array (i.e., no negative numbers)
            if (array[negativeCheckValueIndex] < 0) {
                // DESC: If number is negative, then update the `duplicateValue`
                // ... variable and return it
                duplicateValue = negativeCheckValueIndex + 1;
                return duplicateValue;
            } else {
                // DESC: Else (i.e., number is positive), flip the number to
                // ... negative to indicate that it was previously encountered,
                // ... which will be caught on subsequent iterations by the
                // ... initial `if`
                negativeCheckValue = array[negativeCheckValueIndex];
                negativeCheckValue = negativeCheckValue * -1;
                array[negativeCheckValueIndex] = negativeCheckValue;
            }
        }

        return duplicateValue;
    }
}
