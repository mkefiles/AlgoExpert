package Medium;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a function that takes in a non-empty array of arbitrary intervals,
 * merges any overlapping intervals, and returns the new intervals in no
 * particular order.
 *
 * Each interval `interval` is an array of two integers, with `interval[0]`
 * as the start of the interval and `interval[1]` as the end of the interval.
 *
 * Note that back-to-back intervals aren't considered to be overlapping. For
 * example, `[1, 5]` and `[6, 7]` aren't overlapping; however, `[1, 6]` and
 * `[6, 7]` are indeed overlapping.
 *
 * Also note that the start of any particular interval will always be less than
 * or equal to the end of that interval.
 *
 * SAMPLE INPUT:
 * intervals = [[1, 2], [3, 5], [4, 7], [6, 8], [9, 10]]
 *
 * SAMPLE OUTPUT:
 * [[1, 2], [3, 8], [9, 10]]
 * // Merge the intervals [3, 5], [4, 7], and [6, 8].
 * // The intervals could be ordered differently.
 */

public class MergeOverlappingIntervals {

    public int[][] mergeOverlappingIntervals(int[][] intervals) {
        // DESC: Initialize necessary variables
        int arrayLength = intervals.length;
        ArrayList<ArrayList<Integer>> outputArrayList = new ArrayList<>();
        int arrayListSize = outputArrayList.size();
        int arrayHolder01A = 0;
        int arrayHolder01B = 0;
        int arrayHolder02A = 0;
        int arrayHolder02B = 0;
        int maximumOnePosition = 0;

        // DESC: Sort the interval arrays
        // NOTE: This uses a "Bubble Sort" algorithm
        for (int i = 0; i < arrayLength; i++) {
            for (int j = 0; j < arrayLength - 1; j++) {
                // DESC: Sort in ASC order (check if value 01 is
                // ... greater-than value 02)
                if (intervals[j][0] > intervals[j + 1][0]) {
                    // DESC: Use place-holder arrays
                    arrayHolder01A = intervals[j][0];
                    arrayHolder01B = intervals[j][1];
                    arrayHolder02A = intervals[j + 1][0];
                    arrayHolder02B = intervals[j + 1][1];

                    // DESC: Re-assign values
                    intervals[j][0] = arrayHolder02A;
                    intervals[j][1] = arrayHolder02B;
                    intervals[j + 1][0] = arrayHolder01A;
                    intervals[j + 1][1] = arrayHolder01B;
                }
            }
        }

        // DESC: Loop through and merge applicable intervals
        // NOTE: This logic does NOT take an empty input array
        // ... into consideration (based on what the challenge
        // ... suggests)
        for (int i = 0; i < arrayLength; i++) {
            if (arrayListSize == 0) {
                // NOTE: For the first iteration, simply add the interval
                // ... to the ArrayList (there is nothing before it to
                // ... compare it against)
                // DESC: Add the first interval then continue on to next
                // ... iteration (this should only ever run once)
                outputArrayList.add(new ArrayList<Integer>(Arrays.asList(intervals[i][0], intervals[i][1])));
                arrayListSize = arrayListSize + 1;
            } else {
                // NOTE: Once the ArrayList has one (or more) value(s)
                // ... in it, start comparing against those values in lieu
                // ... of the values in the input array

                // DESC: Check if current array value can merge with prior
                // ... ArrayList value
                if (outputArrayList.get(outputArrayList.size() - 1).get(1) < intervals[i][0]) {
                    // NOTE: ArrayList '1' value is smaller than array '0' value
                    // ... so just add array interval to ArrayList
                    outputArrayList.add(new ArrayList<Integer>(Arrays.asList(intervals[i][0], intervals[i][1])));
                } else if (outputArrayList.get(outputArrayList.size() - 1).get(1) == intervals[i][0]) {
                    // NOTE: ArrayList '1' value equals array '0' value so
                    // ... expand the range of the interval in the ArrayList
                    outputArrayList.get(outputArrayList.size() - 1).set(1, intervals[i][1]);
                } else {
                    // NOTE: In this event ArrayList '1' is greater-than array '0'
                    // ... value so we need to update ArrayList '1' with the MAX value
                    // ... between ArrayList '1' and array '1'
                    maximumOnePosition = Math.max(outputArrayList.get(outputArrayList.size() - 1).get(1), intervals[i][1]);
                    outputArrayList.get(outputArrayList.size() - 1).set(1, maximumOnePosition);
                }
            }
        }
        // DESC: Get updated size of ArrayList to create output array
        arrayListSize = outputArrayList.size();
        int[][] outputArray = new int[arrayListSize][2];

        // DESC: Convert ArrayList back to Array
        for (int i = 0; i < arrayListSize; i++) {
            outputArray[i][0] = outputArrayList.get(i).get(0);
            outputArray[i][1] = outputArrayList.get(i).get(1);
        }

        return outputArray;
    }
}