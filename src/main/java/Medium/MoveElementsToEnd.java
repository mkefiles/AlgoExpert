package Medium;

import java.util.List;

/**
 * You're given an array of integers and an integer. Write a function that moves
 * all instances of that integer in the array to the end of the array and returns
 * the array.
 *
 * The function should perform this in place (i.e., it should mutate the input
 * array) and doesn't need to maintain the order of the other integers.
 *
 * SAMPLE INPUT:
 * array = [2, 1, 2, 2, 2, 3, 4, 2]
 * toMove = 2
 *
 * SAMPLE OUTPUT:
 * [1, 3, 4, 2, 2, 2, 2, 2] // the numbers 1, 3, and 4 could be ordered differently
 */

public class MoveElementsToEnd {
    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        // DESC: Initialize necessary variables
        boolean flag = false;
        int arrayLength = array.size();
        int leftCursorIndex = 0;
        int rightCursorIndex = arrayLength - 1;
        Integer leftCursorValue = 0;
        Integer rightCursorValue = 0;

        // DESC: Loop through array (with two cursors)
        while (flag != true) {
            // DESC: Check if cursors have crossed paths
            if (leftCursorIndex > rightCursorIndex) {
                // DESC: Kill the loop once cursors have crossed
                flag = true;
            } else {
                // DESC: Update cursor values
                leftCursorValue = array.get(leftCursorIndex);
                rightCursorValue = array.get(rightCursorIndex);

                // DESC: Check cursor values then increment/decrement
                // ... accordingly and swap values accordingly
                if (rightCursorValue == toMove) {
                    rightCursorIndex = rightCursorIndex - 1;
                } else {
                    if (leftCursorValue == toMove) {
                        array.set(leftCursorIndex, rightCursorValue);
                        array.set(rightCursorIndex, toMove);
                    } else {
                        leftCursorIndex = leftCursorIndex + 1;
                    }
                }
            }
        }

        // DESC: Return provided array (in-place; destructive)
        return array;
    }
}
