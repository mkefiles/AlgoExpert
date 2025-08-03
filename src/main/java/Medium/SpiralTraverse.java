package Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function that takes in an n x m two-dimensional array (that can be
 * square-shaped when n == m) and returns a one-dimensional array of all the
 * array's elements in spiral order.
 *
 * Spiral order starts at the top left corner of the two-dimensional array, goes
 * to the right, and proceeds in a spiral pattern all the way until every element
 * has been visited.
 *
 * SAMPLE INPUT:
 * array = [
 *  [1,   2,  3, 4],
 *  [12, 13, 14, 5],
 *  [11, 16, 15, 6],
 *  [10,  9,  8, 7],
 *  ]
 *
 * SAMPLE OUTPUT:
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
 */

/**
 * ADDITIONAL NOTES:
 * I noticed, after completing the code, that some AlgoExpert tests passed (8 out of 12)
 * and some tests failed. After looking at the tests, I could not see any common
 * denominator that would fail the test, however I did notice that each fail consisted
 * of the spiral going backwards once it completed its initial traversal. Given that
 * knowledge, I implemented a sort of 'cheat' under each loop that kills the looping
 * outright once the size of the output array equals the size of the input array.
 */
public class SpiralTraverse {

    public static List<Integer> spiralTraverse(int[][] array) {
        // DESC: Initialize necessary variables
        int rowCount = array.length;
        int columnCount = array[0].length;
        int matrixValueCounter = rowCount * columnCount;
        boolean flag = false;
        int startingRow = 0;
        int startingColumn = 0;
        int endingRow = rowCount - 1;
        int endingColumn = columnCount - 1;
        List<Integer> output = new ArrayList<Integer>();

        // DESC: Loop until startingRow and endingRow
        // ... cursors cross
        while (flag != true) {
            if (startingRow > endingRow) {
                flag = true;
                break;
            } else if (startingColumn > endingColumn) {
                flag = true;
                break;
            } else {
                // NOTE: Array build is [row][column]
                // DESC: Top row traversal
                for (int i = startingColumn; i <= endingColumn; i++) {
                    // DESC: My 'cheat' implemented
                    if (matrixValueCounter == output.size()) {
                        flag = true;
                        break;
                    }
                    output.add(array[startingRow][i]);
                }

                // DESC: Right column traversal
                // NOTE: This starts ahead one (to not have duplicate values)
                for (int i = startingRow + 1; i <= endingRow; i++) {
                    // DESC: My 'cheat' implemented
                    if (matrixValueCounter == output.size()) {
                        flag = true;
                        break;
                    }
                    output.add(array[i][endingColumn]);
                }

                // DESC: Bottom row traversal (reverse)
                // NOTE: This starts 'behind' one (to not have duplicate values)
                for (int i = endingColumn - 1; i >= startingColumn; i--) {
                    // DESC: My 'cheat' implemented
                    if (matrixValueCounter == output.size()) {
                        flag = true;
                        break;
                    }
                    output.add(array[endingRow][i]);
                }

                // DESC: Left column traversal (reverse)
                // NOTE: This starts 'behind' AND stops prematurely
                // ... one (to not have duplicate values)
                for (int i = endingRow - 1; i >= startingRow + 1; i--) {
                    // DESC: My 'cheat' implemented
                    if (matrixValueCounter == output.size()) {
                        flag = true;
                        break;
                    }
                    output.add(array[i][startingColumn]);
                }

                // DESC: Update starting and ending values
                startingRow = startingRow + 1;
                startingColumn = startingColumn + 1;
                endingRow = endingRow - 1;
                endingColumn = endingColumn - 1;
            }
        }
        return output;
    }
}
