package Medium;

/**
 * Write a function that takes in an array of integers and returns the length of
 * the longest peak in the array.
 *
 * A peak is defined as adjacent integers in the array that are **strictly**
 * increasing until they reach a tip (the highest value in the peak), at which
 * point they become **strictly** decreasing. At least three integers are required to
 * form a peak.
 *
 * For example, the integers `1, 4, 10, 2` form a peak, but the integers `4, 0, 10`
 * don't and neither do the integers `1, 2, 2, 0`. Similarly, the integers `1, 2, 3`
 * don't form a peak because there aren't any strictly decreasing integers after the `3.
 *
 * SAMPLE INPUT:
 * array = [1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3]
 *
 * SAMPLE OUTPUT:
 * 6 // 0, 10, 6, 5, -1, -3
 */

public class LongestPeak {
    public static int longestPeak(int[] array) {
        // DESC: Initialize necessary variable
        int arrayLength = array.length;
        int peakNumber = 0;
        int precedingNumber = 0;
        int succeedingNumber = 0;
        int peakLength = 0;
        int maxPeakLength = 0;
        boolean decrementFlag = false;
        int decrementCounter = 0;
        boolean incrementFlag = true;
        int incrementCounter = 0;

        // DESC: Kill process if arrayLength is insufficient
        // NOTE: At least three integers are required to form a peak
        if (arrayLength < 3) {
            return 0;
        }

        // DESC: Loop through array
        // NOTE: Start at index one and end on the second-to-last
        // ... value that way you can check `precedingNumber` and
        // ... `succeedingNumber`
        for (int i = 1; i < arrayLength - 1; i++) {
            // DESC: Get necessary 'peak' values
            precedingNumber = array[i - 1];
            peakNumber = array[i];
            succeedingNumber = array[i + 1];

            // DESC: Reset flags
            decrementFlag = false;
            incrementFlag = false;

            // DESC: Determine if it meets basic 'peak' criteria
            if ((peakNumber > precedingNumber) && (peakNumber > succeedingNumber)) {
                peakLength = 3;
                // DESC: Count descending values on left-side of current index
                decrementCounter = i - 1;
                while (decrementFlag != true) {
                    // DESC: Ensure loop is not exceeding array bounds
                    if (decrementCounter - 1 < 0) {
                        decrementFlag = true;
                        break;
                    } else {
                        // DESC: Check if values are decreasing (on the left side)
                        if (array[decrementCounter - 1] < array[decrementCounter]) {
                            // DESC: Increment the overall length counter and decrease
                            // ... the cursor
                            peakLength = peakLength + 1;
                            decrementCounter = decrementCounter - 1;
                        } else {
                            // DESC: Values no longer decreasing so flag to stop loop
                            decrementFlag = true;
                        }
                    }
                }

                // DESC: Count descending values on right-side of current index
                incrementCounter = i + 1;
                while (incrementFlag != true) {
                    // DESC: Ensure loop is not exceeding array bounds
                    if (incrementCounter + 1 == arrayLength) {
                        incrementFlag = true;
                        break;
                    } else {
                        // DESC: Check if values are decreasing (on the right side)
                        if (array[incrementCounter + 1] < array[incrementCounter]) {
                            // DESC: Increment the overall length counter and increase
                            // ... the cursor
                            peakLength = peakLength + 1;
                            incrementCounter = incrementCounter + 1;
                        } else {
                            // DESC: Values no longer decreasing so flag to stop loop
                            incrementFlag = true;
                        }
                    }
                }

                // DESC: Update maxPeakLength
                if (peakLength > maxPeakLength) {
                    maxPeakLength = peakLength;
                }
            } else {
                // DESC: Proceed to next iteration
                continue;
            }
        }
        return maxPeakLength;
    }
}
