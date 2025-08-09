package Medium;

/**
 * You're given an unordered list of unique integers `nums` in the range `[1, n]`,
 * where `n` represents the length of `nums + 2`. This means that two numbers in
 * this range are missing from the list.
 *
 * Write a function that takes in this list and returns a new list with the two
 * missing numbers, sorted numerically.
 *
 * SAMPLE INPUT:
 * nums = [1, 4, 3]
 *
 * SAMPLE OUTPUT:
 * [2, 5] // n is 5, meaning the completed list should be [1, 2, 3, 4, 5]
 */

/**
 * ADDITIONAL NOTES:
 * There was a solution that had the same Space-Time Complexity as this,
 * however it used the 'xor' operator, binary and bits. To me, this seemed
 * like added confusion when the solution used in the code below, all else
 * being equal, is easier to comprehend.
 *
 * The concept here is that knowing 'average' between the expected sum and
 * the actual sum helps to determine the general location of the lower missing
 * value and the higher missing value (each should be on either side of the
 * 'average' value)
 */

public class MissingNumbers {
    public int[] missingNumbers(int[] nums) {
        // DESC: Initialize necessary values
        int numsLength = nums.length;
        int[] missingNumbers = new int[2];
        int expectedLength = numsLength + 2;
        int expectedSum = 0;
        int actualSum = 0;
        int differenceOfSums = 0;
        int average = 0;
        int leftSumActual = 0;
        int leftSumExpected = 0;
        int rightSumActual = 0;
        int rightSumExpected = 0;

        // DESC: Remove an empty-array edge-case
        if (numsLength == 0) {
            missingNumbers[0] = 1;
            missingNumbers[1] = 2;
        }

        // DESC: Calculate the expected sum
        // NOTE: The input array, `nums`, contains unique integers ranging
        // ... from 1 through to 'n' (inclusive) where 'n' represents
        // ... the length of `nums + 2`. This means that two numbers in that
        // ... range are missing from the list
        for (int i = 1; i < expectedLength + 1; i++) {
            expectedSum = expectedSum + i;
        }

        // DESC: Calculate the sum of the input array
        for (int i = 0; i < numsLength; i++) {
            actualSum = actualSum + nums[i];
        }

        // DESC: Subtract input-array sum from expected sum then
        // ... calculate the average of the two values (i.e.,
        // ... divide the difference by two)
        differenceOfSums = expectedSum - actualSum;
        average = differenceOfSums / 2;

        // DESC: Determine `leftSumExpected` and `rightSumExpected`
        // NOTE: With the `average` determined, we know that one of
        // ... the missing values should be at/beneath the `average`
        // ... and other other should be above the `average`
        // NOTE: With most programming languages, division of an odd
        // ... number will round down
        for (int i = 1; i < expectedLength + 1; i++) {
            if (i <= average) {
                leftSumExpected = leftSumExpected + i;
            } else {
                rightSumExpected = rightSumExpected + i;
            }
        }

        // DESC: Loop through array to determine missing values
        for (int i = 0; i < numsLength; i++) {
            if (nums[i] <= average) {
                leftSumActual = leftSumActual + nums[i];
            } else {
                rightSumActual = rightSumActual + nums[i];
            }
        }

        // DESC: Determine missing numbers by subtracting left and
        // ... right-side actuals from the left and right-side
        // ... expecteds respectively
        missingNumbers[0] = leftSumExpected - leftSumActual;
        missingNumbers[1] = rightSumExpected - rightSumActual;

        return missingNumbers;
    }
}
