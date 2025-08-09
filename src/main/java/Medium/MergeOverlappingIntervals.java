package Medium;

import java.util.ArrayList;
import java.util.Arrays;

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