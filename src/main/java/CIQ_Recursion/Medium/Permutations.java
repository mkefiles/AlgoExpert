package CIQ_Recursion.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
* Write a function that takes in an array of unique integers and returns an
* array of all permutations of those integers in no particular order.
*
* If the input array is empty, the function should return an empty array.
*
* SAMPLE INPUT:
* array = [1, 2, 3]
*
* SAMPLE OUTPUT:
* [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
*
* SPACE-TIME COMPLEXITY:
* O(n*n!) time | O(n*n!) space - where n is the length of the input array
*
* ADDITIONAL NOTES:
* ...
*
*
*/

public class Permutations {

    private static List<Integer> swap(List<Integer> arr, int i, int j) {
        // DESC: Initialize necessary variables
        int firstPosition = arr.get(i);
        int secondPosition = arr.get(j);

        // DESC: Swap the positions of `i` and `j` in
        // ... the input array then return that updated
        // ... version
        arr.set(i, secondPosition);
        arr.set(j, firstPosition);
        return arr;
    }

    private static void permutationsHelper(int i, List<Integer> arr, List<List<Integer>> perms) {
        // DESC: Initialize necssary variables
        int arraySize = arr.size();

        // DESC: If cursor is at the end of the array, then
        // ... append current values to the `perms` array
        // ... Otherwise, drop into recursion
        if (i == arraySize - 1) {

            // NOTE: It is necessary to make a temporary copy
            // ... of the current `arr` contents because the output
            // ... will otherwise show multiple copies of REFERENCES
            // ... to the `arr`, which will always contain the value(s)
            // ... it ended with
            ArrayList<Integer> tempArr = new ArrayList<Integer>(Collections.nCopies(arraySize, null));
            Collections.copy(tempArr, arr);
            perms.add(tempArr);
        } else {

            // DESC: For each value of `j`, swap the values at `i` and `j`
            // ... feed the data into a recursive structure then, after
            // ... that recursion has reached its conclusion, it swaps
            // ... the characters back to their original location
            for (int j = i; j < arraySize; j++) {
                swap(arr, i, j);
                permutationsHelper(i + 1, arr, perms);
                swap(arr, i, j);
            }
        }
    }

    public static List<List<Integer>> getPermutations(List<Integer> array) {
        // DESC: Initialize necessary variables
        int arraySize = array.size();
        List<List<Integer>> permutations = new ArrayList<List<Integer>>();

        // DESC: Return an empty array IFF the input array
        // ... is empty
        if (arraySize == 0) {
            return permutations;
        }

        // DESC: Pass "base" index value, the input array
        // ... and the output array to the recursive 'helper'
        // ... function
        permutationsHelper(0, array, permutations);

        // DESC: Return the updated output
        return permutations;
    }

    public static void main(String[] args) {
        // TEST 01:
        // Exp. Output: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
        List<Integer> testInput01 = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println("TEST 01: " + getPermutations(testInput01));
    }
}
