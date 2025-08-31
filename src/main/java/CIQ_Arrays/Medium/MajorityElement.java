package CIQ_Arrays.Medium;

/**
 * Write a function that takes in a non-empty, unordered `array` of positive
 * integers and returns the array's majority element without sorting the
 * array and without using more than constant space.
 *
 * An array's majority element is an element of the array that appears in over
 * half of its indices. Note that the most common element of an array (the
 * element that appears the most times in the array) isn't necessarily the
 * array's majority element; for example, the arrays `[3, 2, 2, 1]` and
 * `[3, 4, 2, 2, 1]` both have `2` as their most common element, yet neither
 * of these arrays have a majority element, because neither `2` nor any other
 * element appears in over half of the respective arrays' indices.
 *
 * You can assume that the input array will always have a majority element.
 *
 * SAMPLE INPUT:
 * array = [1, 2, 3, 2, 2, 1, 2]
 *
 * SAMPLE OUTPUT:
 * 2 // 2 occurs in 4/7 array indices, making it the majority element
 */

/**
 * ADDITIONAL NOTES:
 * There was a solution that had the same Space-Time Complexity as this,
 * however it used the 'and' operator, binary and bits. To me, this seemed
 * like added confusion when the solution used in the code below, all else
 * being equal, is easier to comprehend.
 *
 * The concept here is that you, technically, break the input array in smaller
 * sub-arrays (in a sense) then, based on the logic, disregard each sub-array
 * until the end of the input array.
 *
 * The `count` variable should be incremented, by one, whenever a value equals the
 * current `majorityElementValue`. If, however, it does NOT equal the
 * `majorityElementValue`, the `count` should be decremented by one.
 *
 * Whenever "0" is reached, update the `majorityElementValue` to the index of the
 * last element tested + 1 (this 'disregards' all prior values because they cancelled
 * each other out). Once at the end of the input array, the `majorityElementValue`
 * should, in theory, contain the answer to the problem.
 */

public class MajorityElement {
    public int majorityElement(int[] array) {
        // DESC: Initialize necessary variables
        int arrayLength = array.length;
        int majorityElementValue = 0;
        int counter = 0;

        // DESC: Set the majority element to the first element
        // ... in the array and increase the counter by 1
        // NOTE: This enables us to test every subsequent value
        // ... of the array against it then update the value
        // ... as necessary
        majorityElementValue = array[0];
        counter = 1;

        // DESC: If the array length is only one character then
        // ... that character MUST be the majority element,
        // ... else continue along with the logic
        if (arrayLength == 1) {
            return majorityElementValue;
        } else {
            // DESC: Loop through the array starting at index one
            for (int i = 1; i < arrayLength; i++) {
                // NOTE: Whenever the counter is equal to zero that means
                // ... that x value(s) have consecutively countered the
                // ... value that was previously the majority so we need to
                // ... reset the majority to this current interations value
                if (counter == 0) {
                    majorityElementValue = array[i];
                }
                // DESC: If the current value equals the majority value, then
                // ... increase the counter otherwise decrease it
                if (array[i] == majorityElementValue) {
                    counter = counter + 1;
                } else {
                    counter = counter - 1;
                }
            }
        }
        return majorityElementValue;
    }
}