package Hard;

/**
 * Write a function that takes in a non-empty array of non-negative integers and a non-negative integer
 * representing a target sum. The function should find the longest subarray where the values collectively
 * sum up to equal the target sum. Return an array containing the starting index and ending index of this subarray,
 * both inclusive.
 *
 * If there is no subarray that sums up to the target sum, the function should return an
 * empty array. You can assume that the given inputs will only ever have one answer.
 *
 * SAMPLE INPUT:
 * array = [1, 2, 3, 4, 3, 3, 1, 2, 1, 2]
 * targetSum = 10
 *
 * SAMPLE OUTPUT:
 * [4, 8] // The longest subarray that sums to 10 starts at index 4 and ends at index 8
 */

public class LongestSubarrayWithSum {
    public int[] longestSubarrayWithSum(int[] array, int targetSum) {
        // DESC: Initialize necessary variables
        int arrayLength = array.length;
        int iterationLimit = arrayLength - 1;
        int[] outputArray = new int[2];
        int startCursorIndex = 0;
        int endCursorIndex = 1;
        int currentSum = 0;
        int successfulSubarrayLength = 0;
        boolean isCurrentSumSet = false;

        // DESC: EDGE-CASE: Handle an array with one
        // ... element
        if (arrayLength == 1) {
            if (array[0] == targetSum) {
                outputArray[0] = 0;
                outputArray[1] = 0;
                return outputArray;
            } else {
                return new int[] {};
            }
        }

        // DESC: EDGE-CASE: Handle `targetSum` of '0'
        // NOTE: If the `targetSum` is '0', then
        // ... there is no reason to use elaborate
        // ... logic when the only solution that could
        // ... result in '0' is '0' itself (or a series
        // ... of '0')
        if (targetSum == 0) {
            boolean isStartSet = false;
            int zeroCounter = -1;
            // DESC: Loop through elements of array
            // NOTE: The following loop is intended
            // ... to locate the first instance of "0"
            // ... and, if applicable, locate instances
            // ... where there are multiple "0"
            // NOTE: A scenario where there are multiple
            // ... "0's" has not been fully tested for
            // ... all possible options
            for (int cI = 0; cI < arrayLength; cI++) {

                if (array[cI] == 0 && isStartSet == false) {
                    startCursorIndex = cI;
                    zeroCounter = 0;
                    isStartSet = true;
                    continue;
                }
                if (isStartSet == true && array[cI] == 0) {
                    zeroCounter = zeroCounter + 1;
                } else if (isStartSet == true && array[cI] != 0) {
                    zeroCounter = 0;
                    isStartSet = false;
                }
            }
            if (zeroCounter == 0) {
                outputArray[0] = startCursorIndex;
                outputArray[1] = startCursorIndex;
                return outputArray;
            } else if (zeroCounter > 0) {
                outputArray[0] = startCursorIndex;
                outputArray[1] = startCursorIndex + 1;
            } else if (zeroCounter == -1) {
                return new int[] {};
            }
        }

        // DESC: Main loop
        while (true) {
            // DESC: Check if the `startCursorIndex` is
            // ... greater-than, equal-to or less-than the
            // ... `targetSum`
            // NOTE: The thought-process here is that when
            // ... the `startCursorIndex` is greater-than OR
            // ... equal-to the `targetSum`, the `endCursorIndex`
            // ... is irrelevant because it would take the sum
            // ... further away from the `targetSum`
            if (array[startCursorIndex] > targetSum) {
                startCursorIndex = startCursorIndex + 1;
                endCursorIndex = endCursorIndex + 1;
                isCurrentSumSet = false;
            } else if (array[startCursorIndex] == targetSum) {
                outputArray[0] = startCursorIndex;
                outputArray[1] = startCursorIndex;
                successfulSubarrayLength = 1;

                // DESC: EDGE-CASE: 'Artificially' alter the
                // ... output when there are zeroes on the
                // ... left-side of the solution
                // NOTE: This, basically, takes the current
                // ... `startCursorIndex` and counts the number
                // ... of subsequent "0's" located before it
                int leftZeroCounter = 0;
                int decrementer = startCursorIndex - 1;
                while (true) {
                    if (decrementer < 0) {
                        break;
                    } else {
                        if (array[decrementer] != 0) {
                            break;
                        } else {
                            leftZeroCounter = leftZeroCounter + 1;
                            decrementer = decrementer - 1;
                        }
                    }
                }

                // DESC: EDGE-CASE: 'Artificially' alter the
                // ... output when there are zeroes on the
                // ... right-side of the solution
                // NOTE: This, basically, takes the current
                // ... `startCursorIndex` and counts the number
                // ... of subsequent "0's" located after it
                int rightZeroCounter = 0;
                int incrementer = startCursorIndex + 1;
                while(true) {
                    if (incrementer > iterationLimit) {
                        break;
                    } else {
                        if (array[incrementer] != 0) {
                            break;
                        } else {
                            rightZeroCounter = rightZeroCounter + 1;
                            incrementer = incrementer + 1;
                        }
                    }
                }

                // DESC: Add the zero(es) located before and after
                // ... the proposed solution to provide a proper
                // ... output length
                if (leftZeroCounter != 0 || rightZeroCounter != 0) {
                    outputArray[0] = startCursorIndex - leftZeroCounter;
                    outputArray[1] = startCursorIndex + rightZeroCounter;
                    successfulSubarrayLength = (endCursorIndex + rightZeroCounter) - (startCursorIndex - leftZeroCounter) + 1;
                }

                startCursorIndex = startCursorIndex + 1;
                endCursorIndex = endCursorIndex + 1;

                isCurrentSumSet = false;
            } else {
                // DESC: The `isCurrentSumSet` is an intended
                // ... single-run kick-out that basically
                // ... initializes the `currentSum` when the
                // ... cursors are side-by-side so subsequent
                // ... runs can add or subtract to/from the
                // ... `currentSum` properly
                if (isCurrentSumSet == false) {
                    currentSum = array[startCursorIndex] + array[endCursorIndex];
                    isCurrentSumSet = true;
                }

                // DESC: Primary logic that checks if the
                // ... `currentSum` is greater-than, equal-to
                // ... or less-than the `targetSum`
                // NOTE: The general logic is as follows:
                // - If cS > tS, then move startCursor by one
                // - If cS == tS, then update output accordingly
                // ... and move endCursor by one
                // - If cS > tS, then move endCursor by one
                // - At every move, make sure to subtract the
                // ... removed value OR add the appended value
                // ... to the `currentSum`
                if (currentSum > targetSum) {
                    currentSum = currentSum - array[startCursorIndex];
                    startCursorIndex = startCursorIndex + 1;
                } else if (currentSum == targetSum) {

                    // DESC: Ensure that the current successful
                    // ... combination is longer than the length
                    // ... of prior combinations then update the
                    // ... output array
                    if ((endCursorIndex - startCursorIndex) + 1 > successfulSubarrayLength) {
                        outputArray[0] = startCursorIndex;
                        outputArray[1] = endCursorIndex;
                        successfulSubarrayLength = (endCursorIndex - startCursorIndex) + 1;
                    }

                    endCursorIndex = endCursorIndex + 1;

                    // DESC: Handle array-index out-of-bounds
                    // ... issues PRIOR TO updating the `currentSum`
                    // ... with the increased `endCursorIndex`
                    if (endCursorIndex > iterationLimit) {
                        break;
                    } else {
                        currentSum = currentSum + array[endCursorIndex];
                    }

                } else {
                    endCursorIndex = endCursorIndex + 1;

                    // DESC: Handle array-index out-of-bounds
                    // ... issues PRIOR TO updating the `currentSum`
                    // ... with the increased `endCursorIndex`
                    if (endCursorIndex > iterationLimit) {
                        break;
                    } else {
                        currentSum = currentSum + array[endCursorIndex];
                    }
                }
            }
        }

        // DESC: EDGE-CASE: Handle nothing being found
        if (successfulSubarrayLength == 0) {
            return new int[] {};
        }

        return outputArray;
    }
}