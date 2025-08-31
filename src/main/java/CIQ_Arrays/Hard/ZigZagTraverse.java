package CIQ_Arrays.Hard;

/**
 * Write a function that takes in an n x m two-dimensional array (that can be
 * square-shaped when n == m) and returns a one-dimensional array of all the
 * array's elements in zigzag order.
 *
 * Zigzag order starts at the top-left corner of the two-dimensional array, goes
 * down by one element, and proceeds in a zigzag pattern all the way to the
 * bottom-right corner.
 *
 * SAMPLE INPUT:
 * [
 *  [1,  3,  4, 10],
 *  [2,  5,  9, 11],
 *  [6,  8, 12, 15],
 *  [7, 13, 14, 16],
 * ]
 *
 * SAMPLE OUTPUT:
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
 */

import java.util.ArrayList;
import java.util.List;

/**
 * ADDITIONAL NOTES:
 * - For this problem, it is necessary to determine when the
 * cursor is in the FIRST COLUMN and LAST ROW, the FIRST ROW
 * and LAST COLUMN, the FIRST COLUMN (not LAST ROW), the FIRST
 * ROW, the LAST ROW or the LAST COLUMN then change the direction
 * accordingly
 * - At any given point, the cursor can ONLY move in the
 * following directions: DOWN, DIAGONAL-UP, DIAGONAL-DOWN
 * and RIGHT (hence the Enum)
 * - Movement directions are as follows:
 * -- DOWN: Y + 1, X + 0
 * -- DIAG_UP: Y - 1, X + 1
 * -- RIGHT: Y + 0, X + 1
 * -- DIAG_DOWN: Y + 1, X - 1
 */

public class ZigZagTraverse {
    public static List<Integer> zigzagTraverse(List<List<Integer>> array) {
        // DESC: Initialize necessary variables
        int arrayLengthX = array.get(0).size();
        int arrayLengthY = array.size();
        int numberOfValues = arrayLengthX * arrayLengthY;
        enum Direction {
            DOWN,
            DIAG_UP,
            DIAG_DOWN,
            RIGHT
        }
        int xIndex = 0;
        int yIndex = 0;
        Direction currentDirection = Direction.DOWN;
        List<Integer> oneDimensionalOutput = new ArrayList<Integer>();
        boolean flag = true;

        // DESC: If array-dimension is n x 1, then zig-zag
        // ... is not necessary
        // NOTE: This handles a single-column input-array
        // ... and simple converts it from a 2D-vertical
        // ... array to a 1D-horizontal array
        if (arrayLengthY > 1 && arrayLengthX == 1) {
            for (int i = 0; i < arrayLengthY; i++) {
                oneDimensionalOutput.add(array.get(i).get(0));
            }
            return oneDimensionalOutput;
        }


        while (flag == true) {
            // DESC: Get current value based on x and y indices
            oneDimensionalOutput.add(array.get(yIndex).get(xIndex));

            // DESC: Check if the 'output' array contains the same
            // ... number of values as the 2D array then flip the flag
            // ... to kill the loop
            if (oneDimensionalOutput.size() == numberOfValues) {
                flag = false;
            }

            // DESC: Logic for handling a 'DOWN' direction
            // NOTE: This is the starting value because the first
            // ... move is always in the 'DOWN' direction
            // NOTE: The general logic used throughout is:
            // ... Step 01: Update the cursor-location
            // ... Step 02: Flip the direction
            // ... Both of these steps alter the logic on
            // ... the subsequent loop
            if (currentDirection == Direction.DOWN) {
                // DESC: Check if moving DOWN one is within
                // ... array bounds (if not, then move RIGHT)
                if (yIndex + 1 == arrayLengthY) {
                    if (xIndex + 1 == arrayLengthX) {
                        break;
                    } else {
                        yIndex = yIndex + 0;
                        xIndex = xIndex + 1;
                        currentDirection = Direction.RIGHT;
                        continue;
                    }
                }

                // NOTE: This handles elements in the FIRST COLUMN
                if ((xIndex == 0) && (yIndex + 1 <= arrayLengthY - 1)) {
                    yIndex = yIndex + 1;
                    xIndex = xIndex + 0;
                    currentDirection = Direction.DIAG_UP;
                    continue;
                }

                // NOTE: This handles elements in the LAST COLUMN
                if ((xIndex == arrayLengthX - 1) && (yIndex + 1 <= arrayLengthY - 1)) {
                    yIndex = yIndex + 1;
                    xIndex = xIndex + 0;
                    currentDirection = Direction.DIAG_DOWN;
                    continue;
                }
            }

            // DESC: Logic for handling a 'DIAG_UP' direction
            if (currentDirection == Direction.DIAG_UP) {

                // NOTE: This handles all elements in the middle
                // ... of the array
                if ((yIndex - 1 > 0) && (xIndex + 1 < arrayLengthX - 1)) {
                    yIndex = yIndex - 1;
                    xIndex = xIndex + 1;
                    continue;
                }

                // NOTE: This handles elements that are in the
                // ... LAST COLUMN (not the FIRST ROW)
                if ((yIndex - 1 > 0) && (xIndex + 1 == arrayLengthX - 1)) {
                    yIndex = yIndex - 1;
                    xIndex = xIndex + 1;
                    currentDirection = Direction.DOWN;
                    continue;
                }

                // NOTE: This handles elements that are in the
                // ... FIRST ROW and LAST COLUMN
                if ((yIndex - 1 == 0) && (xIndex + 1 == arrayLengthX - 1)) {
                    yIndex = yIndex - 1;
                    xIndex = xIndex + 1;
                    currentDirection = Direction.DOWN;
                    continue;
                }

                // NOTE: This handles elements that are in the
                // ... FIRST ROW
                if ((yIndex - 1 == 0) && (xIndex + 1 < arrayLengthX - 1)) {
                    yIndex = yIndex - 1;
                    xIndex = xIndex + 1;
                    currentDirection = Direction.RIGHT;
                    continue;
                }
            }

            // DESC: Logic for handling a 'DIAG_DOWN' direction
            if (currentDirection == Direction.DIAG_DOWN) {

                // NOTE: This handles all elements in the middle
                // ... of the array
                if ((yIndex + 1 < arrayLengthY - 1) && (xIndex - 1 > 0)) {
                    yIndex = yIndex + 1;
                    xIndex = xIndex - 1;
                    continue;
                }

                // NOTE: This handles all elements in the FIRST
                // ... COLUMN (not the LAST ROW)
                if ((yIndex + 1 < arrayLengthY - 1) && (xIndex - 1 == 0)) {
                    yIndex = yIndex + 1;
                    xIndex = xIndex - 1;
                    currentDirection = Direction.DOWN;
                    continue;
                }

                // NOTE: This handles all elements in the FIRST
                // ... COLUMN and LAST ROW
                if ((yIndex + 1 == arrayLengthY - 1) && (xIndex - 1 == 0)) {
                    yIndex = yIndex + 1;
                    xIndex = xIndex - 1;
                    currentDirection = Direction.RIGHT;
                    continue;
                }

                // NOTE: This handles all elements in the LAST ROw (
                // ... not the FIRST COLUMN)
                if ((yIndex + 1 == arrayLengthY - 1) && (xIndex - 1 > 0)) {
                    yIndex = yIndex + 1;
                    xIndex = xIndex - 1;
                    currentDirection = Direction.RIGHT;
                    continue;
                }
            }

            // DESC: Logic for handling a 'RIGHT' direction
            if (currentDirection == Direction.RIGHT) {

                // NOTE: If the element is in the FIRST ROW
                // ... we need to check, first, if there is
                // ... a second row then we need to have logic
                // ... to check if the cursor can proceed any
                // ... further to the right
                if (yIndex == 0) {
                    if (yIndex + 1 == arrayLengthY) {
                        if (xIndex + 1 <= arrayLengthX - 1) {
                            yIndex = yIndex + 0;
                            xIndex = xIndex + 1;
                            continue;
                        }
                    } else {
                        if (xIndex + 1 <= arrayLengthX - 1) {
                            yIndex = yIndex + 0;
                            xIndex = xIndex + 1;
                            currentDirection = Direction.DIAG_DOWN;
                            continue;
                        }
                    }
                }
                // NOTE: This handles elements in the LAST ROW
                if ((yIndex == arrayLengthY - 1) && (xIndex + 1 <= arrayLengthX - 1)) {
                    yIndex = yIndex + 0;
                    xIndex = xIndex + 1;
                    currentDirection = Direction.DIAG_UP;
                    continue;
                }
            }
        }
        return oneDimensionalOutput;
    }
}